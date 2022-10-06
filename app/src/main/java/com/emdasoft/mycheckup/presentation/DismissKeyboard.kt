package com.emdasoft.mycheckup.presentation

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class DismissKeyboard {
    fun dismissKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (null != activity.currentFocus) imm.hideSoftInputFromWindow(
            activity.currentFocus!!.applicationWindowToken, 0
        )
    }
}