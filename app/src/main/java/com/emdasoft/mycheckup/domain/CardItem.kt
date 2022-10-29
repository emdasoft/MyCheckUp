package com.emdasoft.mycheckup.domain

data class CardItem(
    val title: String = UNDEFINED_TITLE,
    var amount: Double = DEFAULT_AMOUNT,
    val currency: String = DEFAULT_CURRENCY,
    val category: String = DEFAULT_CATEGORY,
    var id: Int = UNDEFINED_ID,
) {
    companion object {
        const val UNDEFINED_ID = -1
        const val UNDEFINED_TITLE = "Untitled"
        const val DEFAULT_AMOUNT = 0.0
        const val DEFAULT_CURRENCY = "BYN"
        const val DEFAULT_CATEGORY = "Regular"
    }
}
