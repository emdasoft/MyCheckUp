package com.emdasoft.mycheckup.domain

class SpendMoneyUseCase(private val cardListRepository: CardListRepository) {

    fun spendMoney(amount: Double, card: CardItem) {

        cardListRepository.spendMoney(amount, card)

    }

}