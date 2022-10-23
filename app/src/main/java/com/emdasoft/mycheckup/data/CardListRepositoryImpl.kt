package com.emdasoft.mycheckup.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emdasoft.mycheckup.data.db.dao.CardDao
import com.emdasoft.mycheckup.domain.CardItem
import com.emdasoft.mycheckup.domain.CardListRepository
import kotlin.math.roundToInt

class CardListRepositoryImpl(private val dao: CardDao) : CardListRepository {

    private val cardListLD = MutableLiveData<List<CardItem>>()
    private val cardList = mutableListOf<CardItem>()
    private val currentBalanceLD = MutableLiveData<String>()

    //временно, пока нет настоящих данных, берем из готового списка
//    init {
//        for (item in CardsData.getCardsList()) {
//            addCard(item)
//        }
//    }

    override fun addCard(card: CardItem) {
        cardList.add(card)
        updateList()
        updateCurrentBalance()
    }

    override fun removeCard(card: CardItem) {
        cardList.remove(card)
        updateList()
        updateCurrentBalance()
    }

    override fun editCard(card: CardItem) {
        val oldElement = getCardItem(card.id)
        removeCard(oldElement)
        addCard(card)
        updateCurrentBalance()
    }

    override fun getCardItem(cardId: Int): CardItem {
        return cardList.find {
            it.id == cardId
        } ?: throw RuntimeException("Element with id $cardId not found!")
    }

    override suspend fun getCardList(): LiveData<List<CardItem>> {
        return dao.getCardList()
    }

    private fun updateList() {
        cardListLD.value = cardList.toList().sortedBy { it.id }
    }

    private fun updateCurrentBalance() {
        var total = 0.00

        for (item in cardList) {
            if (item.currency == "USD" || item.currency == "EUR") {
                total += item.amount
            }
            if (item.currency == "BYN") {
                total += item.amount / 2.5
            }
        }
        total = (total * 100).roundToInt() / 100.00
        currentBalanceLD.value = total.toString()
    }

    override fun getCurrentBalance(): LiveData<String> {
        return currentBalanceLD
    }

    override fun getCategoryBalance(): ArrayList<String> {
        var regular = 0.00
        var reserve = 0.00
        var service = 0.00
        var saving = 0.00
        val result = arrayListOf<String>()
        for (item in cardList) {
            when (item.category) {
                "Regular" -> {
                    regular += item.amount
                }
                "Reserve" -> {
                    reserve += item.amount
                }
                "ServiceMT" -> {
                    service += item.amount
                }
                "Saving" -> {
                    saving += if (item.currency == "BYN") {
                        item.amount / 2.5
                    } else item.amount
                }
            }
        }
        saving = (((saving * 100).roundToInt()) / 100.00)
        reserve = (((reserve * 100).roundToInt()) / 100.00)
        service = (((service * 100).roundToInt()) / 100.00)
        regular = (((regular * 100).roundToInt()) / 100.00)
        result.add(saving.toString())
        result.add(reserve.toString())
        result.add(service.toString())
        result.add(regular.toString())
        return result
    }


    override fun getBudget(receiveAmount: Double): ArrayList<String> {
        val toSaving = ((receiveAmount * 0.2) * 100).roundToInt() / 100.00
        val toReserve = ((receiveAmount * 0.3) * 100).roundToInt() / 100.00
        val toServiceMT = ((receiveAmount * 0.05) * 100).roundToInt() / 100.00
        val toRegular = ((receiveAmount * 0.45) * 100).roundToInt() / 100.00
        val result = arrayListOf<String>()
        result.add(toSaving.toString())
        result.add(toReserve.toString())
        result.add(toServiceMT.toString())
        result.add(toRegular.toString())
        return result
    }

}