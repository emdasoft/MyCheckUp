package com.emdasoft.mycheckup.domain

import androidx.lifecycle.LiveData

class GetCardListUseCase(private val cardListRepository: CardListRepository) {

    fun getCardList(): LiveData<List<CardItem>> {

        return cardListRepository.getCardList()

    }

}