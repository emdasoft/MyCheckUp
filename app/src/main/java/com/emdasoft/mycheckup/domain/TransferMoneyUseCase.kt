package com.emdasoft.mycheckup.domain

class TransferMoneyUseCase(private val cardsRepository: CardsRepository) {

    fun transferMoney(amount: Double, source: Card, target: Card) {

        cardsRepository.transferMoney(amount, source, target)

    }

}