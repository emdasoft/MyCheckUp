package com.emdasoft.mycheckup.domain

class TransferMoneyUseCase {

    fun transferMoney(amount: Double, source: Card, target: Card) {
        source.amount -= amount
        target.amount += amount

    }
}