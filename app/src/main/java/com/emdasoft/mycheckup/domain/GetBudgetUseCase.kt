package com.emdasoft.mycheckup.domain

class GetBudgetUseCase(private val cardListRepository: CardListRepository) {

    fun getBudget(receiveAmount: Double): ArrayList<String> {

        return cardListRepository.getBudget(receiveAmount)

    }

}