package com.emdasoft.mycheckup.domain

class GetCardItemUseCase(private val cardListRepository: CardListRepository) {

    fun getCardItem(cardId: Int): CardItem {

        return cardListRepository.getCardItem(cardId)

    }

}