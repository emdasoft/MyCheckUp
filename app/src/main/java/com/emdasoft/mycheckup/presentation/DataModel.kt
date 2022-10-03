package com.emdasoft.mycheckup.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.mycheckup.data.CardListRepositoryImpl
import com.emdasoft.mycheckup.data.CardsData
import com.emdasoft.mycheckup.domain.*

open class DataModel : ViewModel() {

    private val repository = CardListRepositoryImpl

    private val getCardListUseCase = GetCardListUseCase(repository)
    private val removeCardItemUseCase = RemoveCardItemUseCase(repository)
    private val editCardItemUseCase = EditCardItemUseCase(repository)
    private val receiveMoneyUseCase = ReceiveMoneyUseCase(repository)
    private val spendMoneyUseCase = SpendMoneyUseCase(repository)
    private val transferMoneyUseCase = TransferMoneyUseCase(repository)
    private val getCategoryBalanceUseCase = GetCategoryBalanceUseCase(repository)
    private val getCurrentBalanceUseCase = GetCurrentBalanceUseCase(repository)

    val checkData: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    val cardsList: MutableLiveData<List<CardItem>> by lazy {
        MutableLiveData<List<CardItem>>()
    }

    val currentBalance: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val categoryBalance : MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    fun getCardList() {
        val list = getCardListUseCase.getCardList()
        cardsList.value = list
    }

    fun getCurrentBalance() {
        val balance = getCurrentBalanceUseCase.getCurrentBalance()
        currentBalance.value = balance
    }

    fun getCategoryBalance() {
        val catBalance = getCategoryBalanceUseCase.getCategoryBalance()
        categoryBalance.value = catBalance
    }

}