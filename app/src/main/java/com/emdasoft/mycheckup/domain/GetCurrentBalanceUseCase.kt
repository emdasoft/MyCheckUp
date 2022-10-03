package com.emdasoft.mycheckup.domain

class GetCurrentBalanceUseCase(private val cardListRepository: CardListRepository) {

    fun getCurrentBalance(): String {

        return cardListRepository.getCurrentBalance()

    }

}