package com.emdasoft.mycheckup.presentation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Fragment.openFragment(fragment: Fragment, placeHolder: Int) {
    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        ?.replace(placeHolder, fragment)
        ?.commit()
}

fun Fragment.openFragmentWithDetach(fragment: Fragment, placeHolder: Int) {
    activity?.supportFragmentManager
        ?.beginTransaction()
        ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        ?.detach(this)
        ?.replace(placeHolder, fragment)
        ?.commit()
}

fun AppCompatActivity.openFragment(fragment: Fragment, placeHolder: Int) {
    supportFragmentManager
        .beginTransaction()
        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        .replace(placeHolder, fragment)
        .commit()
}

