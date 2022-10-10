package com.emdasoft.mycheckup.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.mycheckup.data.CardListRepositoryImpl
import com.emdasoft.mycheckup.domain.*

open class DataModel : ViewModel() {

    private val repository = CardListRepositoryImpl

    private val getCardListUseCase = GetCardListUseCase(repository)
    private val removeCardItemUseCase = RemoveCardItemUseCase(repository)
    private val getCategoryBalanceUseCase = GetCategoryBalanceUseCase(repository)
    private val getCurrentBalanceUseCase = GetCurrentBalanceUseCase(repository)
    private val getBudgetUseCase = GetBudgetUseCase(repository)
    private val editCardItemUseCase = EditCardItemUseCase(repository)
    private val addCardItemUseCase = AddCardItemUseCase(repository)

    val budget: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    val cardsList = getCardListUseCase.getCardList()

    val currentBalance = getCurrentBalanceUseCase.getCurrentBalance()

    val categoryBalance: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    fun addCardItem(cardItem: CardItem) {
        addCardItemUseCase.addCard(cardItem)
    }

    fun getCategoryBalance() {
        val catBalance = getCategoryBalanceUseCase.getCategoryBalance()
        categoryBalance.value = catBalance
    }

    fun removeCardItem(cardItem: CardItem) {
        removeCardItemUseCase.removeCard(cardItem)
        getCategoryBalance()
    }

    fun receiveMoney(amount: Double, cardItem: CardItem) {
        val newAmount = cardItem.amount + amount
        val newItem = cardItem.copy(amount = newAmount)
        editCardItemUseCase.editCard(newItem)
        getCategoryBalance()
    }

    fun spendMoney(amount: Double, cardItem: CardItem) {
        if (cardItem.amount >= amount) {
            val newAmount = cardItem.amount - amount
            val newItem = cardItem.copy(amount = newAmount)
            editCardItemUseCase.editCard(newItem)
            getCategoryBalance()
        }
    }

    fun transferMoney(amount: Double, source: CardItem, target: CardItem) {
        if (source.amount >= amount) {
            spendMoney(amount, source)
            receiveMoney(amount, target)
            getCategoryBalance()
        }
    }

    fun getBudget(receiveAmount: Double) {
        val budgetByCategory = getBudgetUseCase.getBudget(receiveAmount)
        budget.value = budgetByCategory
    }

}
