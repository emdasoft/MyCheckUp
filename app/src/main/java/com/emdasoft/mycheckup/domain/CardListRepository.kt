package com.emdasoft.mycheckup.domain

interface CardListRepository {

    fun addCard(card: CardItem)

    fun removeCard(card: CardItem)

    fun editCard(card: CardItem)

    fun getCardItem(cardId: Int): CardItem

    fun getCardList(): List<CardItem>

    fun getCurrentBalance(): String

    fun getCategoryBalance(): ArrayList<String>

    fun getBudget(receiveAmount: Double): ArrayList<String>

}