package com.emdasoft.mycheckup.domain

class EditCardItemUseCase(private val cardListRepository: CardListRepository) {

    fun editCard(card: CardItem) {

        cardListRepository.editCard(card)

    }

}