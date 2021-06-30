package com.emdasoft.mycheckup.domain

class RemoveCardItemUseCase(private val cardListRepository: CardListRepository) {

    fun removeCard(card: CardItem) {

        cardListRepository.removeCard(card)

    }

}