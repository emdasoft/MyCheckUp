package com.emdasoft.mycheckup.data

import com.emdasoft.mycheckup.domain.CardItem
import com.emdasoft.mycheckup.domain.CardListRepository
import kotlin.math.roundToInt

object CardListRepositoryImpl : CardListRepository {

    private var cardList = mutableListOf<CardItem>()
    private var autoIncrementId = 0

    //временно, пока нет настоящих данных, берем из готового списка
    init {
        for (item in CardsData.getCardsList()) {
            addCard(item)
        }
    }

    override fun addCard(card: CardItem) {
        if (card.id == CardItem.UNDEFINED_ID) {
            card.id = autoIncrementId++
        }
        cardList.add(card)
    }

    override fun removeCard(card: CardItem) {
        cardList.remove(card)
    }

    override fun editCard(card: CardItem) {
        val oldElement = getCardItem(card.id)
        cardList.remove(oldElement)
        cardList.add(card)
    }

    override fun getCardItem(cardId: Int): CardItem {
        return cardList.find {
            it.id == cardId
        } ?: throw RuntimeException("Element with id $cardId not found!")
    }

    override fun receiveMoney(amount: Double, card: CardItem) {
        card.amount += amount
    }

    override fun spendMoney(amount: Double, card: CardItem) {
        card.amount -= amount
    }

    override fun transferMoney(amount: Double, source: CardItem, target: CardItem) {
        source.amount -= amount
        target.amount += amount
    }

    override fun getCardList(): List<CardItem> {
        return cardList.toList()
    }

    override fun getCurrentBalance(): String {
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
        return total.toString()
    }

    override fun getCategoryBalance(): ArrayList<String> {
        var pov = 0.00
        var res = 0.00
        var mt = 0.00
        var seb = 0.00
        val result = arrayListOf<String>()
        for (item in cardList) {
            when (item.category) {
                "POV" -> {
                    pov += item.amount
                }
                "RES" -> {
                    res += item.amount
                }
                "MT" -> {
                    mt += item.amount
                }
                "SEB" -> {
                    seb += if (item.currency == "BYN") {
                        item.amount / 2.5
                    } else item.amount
                }
            }
        }
        seb = ((seb * 100).roundToInt() / 100).toDouble()
        result.add(seb.toString())
        result.add(res.toString())
        result.add(mt.toString())
        result.add(pov.toString())
        return result
    }

}