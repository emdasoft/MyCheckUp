package com.emdasoft.mycheckup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.mycheckup.domain.Card

open class DataModel : ViewModel() {

    val checkData: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    val cardsList: MutableLiveData<ArrayList<Card>> by lazy {
        MutableLiveData<ArrayList<Card>>()
    }

}