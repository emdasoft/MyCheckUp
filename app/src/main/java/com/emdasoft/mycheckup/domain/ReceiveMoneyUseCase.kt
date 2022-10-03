package com.emdasoft.mycheckup.domain

class ReceiveMoneyUseCase(private val cardRepository: CardsRepository) {

    fun receiveMoney(amount: Double, card: Card) {

        cardRepository.receiveMoney(amount, card)

    }

}