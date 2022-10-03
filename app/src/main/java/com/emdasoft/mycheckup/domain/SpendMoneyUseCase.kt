package com.emdasoft.mycheckup.domain

class SpendMoneyUseCase(private val cardsRepository: CardsRepository) {

    fun spendMoney(amount: Double, card: Card) {

        cardsRepository.spendMoney(amount, card)

    }

}