package com.emdasoft.mycheckup.data

import com.emdasoft.mycheckup.domain.CardItem
import com.emdasoft.mycheckup.domain.CardListRepository

object CardListRepositoryImpl : CardListRepository {

    private val cardList = mutableListOf<CardItem>()
    private var autoIncrementId = 0


    override fun addCard(card: CardItem) {
        if (card.id == CardItem.UNDEFINED_ID){
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

}