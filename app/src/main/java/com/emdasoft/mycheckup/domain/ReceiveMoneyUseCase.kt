package com.emdasoft.mycheckup.domain

class ReceiveMoneyUseCase(private val cardListRepository: CardListRepository) {

    fun receiveMoney(amount: Double, card: CardItem) {

        cardListRepository.receiveMoney(amount, card)

    }

}