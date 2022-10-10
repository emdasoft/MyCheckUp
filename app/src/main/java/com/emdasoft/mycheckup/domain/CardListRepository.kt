package com.emdasoft.mycheckup.domain

import androidx.lifecycle.LiveData

interface CardListRepository {

    fun addCard(card: CardItem)

    fun removeCard(card: CardItem)

    fun editCard(card: CardItem)

    fun getCardItem(cardId: Int): CardItem

    fun getCardList(): LiveData<List<CardItem>>

    fun getCurrentBalance(): String

    fun getCategoryBalance(): ArrayList<String>

    fun getBudget(receiveAmount: Double): ArrayList<String>

}