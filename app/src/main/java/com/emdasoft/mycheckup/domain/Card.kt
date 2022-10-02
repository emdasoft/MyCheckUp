package com.emdasoft.mycheckup.domain

data class Card (
    val id: Int,
    val title: String,
    var amount: Double,
    val currency: String,
    val category: String
)