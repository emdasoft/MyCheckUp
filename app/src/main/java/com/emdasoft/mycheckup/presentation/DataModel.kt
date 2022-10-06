package com.emdasoft.mycheckup.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.mycheckup.data.CardListRepositoryImpl
import com.emdasoft.mycheckup.domain.*

open class DataModel : ViewModel() {

    private val repository = CardListRepositoryImpl

    private val getCardListUseCase = GetCardListUseCase(repository)
    private val removeCardItemUseCase = RemoveCardItemUseCase(repository)
    private val receiveMoneyUseCase = ReceiveMoneyUseCase(repository)
    private val spendMoneyUseCase = SpendMoneyUseCase(repository)
    private val transferMoneyUseCase = TransferMoneyUseCase(repository)
    private val getCategoryBalanceUseCase = GetCategoryBalanceUseCase(repository)
    private val getCurrentBalanceUseCase = GetCurrentBalanceUseCase(repository)
    private val getBudgetUseCase = GetBudgetUseCase(repository)

    val budget: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    val cardsList: MutableLiveData<List<CardItem>> by lazy {
        MutableLiveData<List<CardItem>>()
    }

    val currentBalance: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val categoryBalance: MutableLiveData<ArrayList<String>> by lazy {
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

    fun removeCardItem(cardItem: CardItem) {
        removeCardItemUseCase.removeCard(cardItem)
        getCardList()
        getCategoryBalance()
    }

    fun receiveMoney(amount: Double, cardItem: CardItem) {
        receiveMoneyUseCase.receiveMoney(amount, cardItem)
        getCardList()
        getCategoryBalance()
    }

    fun spendMoney(amount: Double, cardItem: CardItem) {
        spendMoneyUseCase.spendMoney(amount, cardItem)
        getCardList()
        getCategoryBalance()
    }

    fun transferMoney(amount: Double, source: CardItem, target: CardItem) {
        transferMoneyUseCase.transferMoney(amount, source, target)
        getCardList()
        getCategoryBalance()
    }

    fun getBudget(receiveAmount: Double) {
        TODO()
    }

}