package com.emdasoft.mycheckup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emdasoft.mycheckup.domain.CardItem

open class DataModel : ViewModel() {

    val checkData: MutableLiveData<ArrayList<String>> by lazy {
        MutableLiveData<ArrayList<String>>()
    }

    val cardsList: MutableLiveData<ArrayList<CardItem>> by lazy {
        MutableLiveData<ArrayList<CardItem>>()
    }

}