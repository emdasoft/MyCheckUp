package com.emdasoft.mycheckup.domain

class AddCardItemUseCase(private val cardListRepository: CardListRepository) {

    fun addCard(card: CardItem) {

        cardListRepository.addCard(card)

    }

}