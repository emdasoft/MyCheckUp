package com.emdasoft.mycheckup.domain

class GetCardListUseCase(private val cardListRepository: CardListRepository) {

    fun getCardList(): List<CardItem>{

        return cardListRepository.getCardList()

    }

}