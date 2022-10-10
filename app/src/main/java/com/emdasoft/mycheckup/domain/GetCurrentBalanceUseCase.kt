package com.emdasoft.mycheckup.domain

import androidx.lifecycle.LiveData

class GetCurrentBalanceUseCase(private val cardListRepository: CardListRepository) {

    fun getCurrentBalance(): LiveData<String> {

        return cardListRepository.getCurrentBalance()

    }

}