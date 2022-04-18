package com.emdasoft.mycheckup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel : ViewModel() {
    val sebData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val resData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val mtData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val povData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

}