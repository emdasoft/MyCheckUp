package com.emdasoft.mycheckup.domain

class TransferMoneyUseCase(private val cardListRepository: CardListRepository) {

    fun transferMoney(amount: Double, source: CardItem, target: CardItem) {

        cardListRepository.transferMoney(amount, source, target)

    }

}