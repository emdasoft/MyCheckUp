package com.emdasoft.mycheckup.data

import com.emdasoft.mycheckup.domain.CardItem

// временный поставщик данных для дальнейшего проектирования приложения
object CardsData {

    fun getCardsList() = mutableListOf(
        CardItem("БелВЭБ", 27.03, "BYN", "Regular"),
        CardItem("Наличные BYN", 2.50, "BYN", "Regular"),
        CardItem("Cashalot", 0.00, "BYN", "Regular"),
        CardItem( "МТ", 107.31, "BYN", "ServiceMT"),
        CardItem( "Резерв", 400.0, "BYN", "Reserve"),
        CardItem( "Наличные USD", 2950.0, "USD", "Saving"),
        CardItem( "Мелочь USD", 350.0, "USD", "Saving"),
        CardItem( "Наличные EUR", 980.0, "EUR", "Saving"),
        CardItem( "FinStore Инвестиции", 740.0, "USD", "Saving"),
        CardItem( "FinStore Доход", 1.75, "USD", "Saving"),
        CardItem( "Отложенные BYN", 500.0, "BYN", "Saving"),
        CardItem( "USD на карте", 518.19, "USD", "Saving")
    )
}