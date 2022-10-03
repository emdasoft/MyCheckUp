package com.emdasoft.mycheckup.domain

interface CardsRepository {

    fun receiveMoney(amount: Double, card: Card)

    fun spendMoney(amount: Double, card: Card)

    fun transferMoney(amount: Double, source: Card, target: Card)

    fun showAllCategories(): ArrayList<String>

}