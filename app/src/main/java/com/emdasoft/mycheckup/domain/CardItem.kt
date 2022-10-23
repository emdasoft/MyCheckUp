package com.emdasoft.mycheckup.domain

import androidx.room.Entity

@Entity
data class CardItem (
    val title: String,
    var amount: Double,
    val currency: String,
    val category: String,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
