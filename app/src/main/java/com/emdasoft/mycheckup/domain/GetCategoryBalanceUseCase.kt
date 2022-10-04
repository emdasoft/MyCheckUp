package com.emdasoft.mycheckup.domain

class GetCategoryBalanceUseCase(private val cardListRepository: CardListRepository) {

    fun getCategoryBalance(): ArrayList<String> {

        return cardListRepository.getCategoryBalance()

    }

}