package com.emdasoft.mycheckup.data

import com.emdasoft.mycheckup.domain.CardItem

// временный поставщик данных для дальнейшего проектирования приложения
object CardsData {

    fun getCardsList() = mutableListOf(
        CardItem("БелВЭБ", 254.18, "BYN", "Regular"),
        CardItem("Наличные BYN", 0.70, "BYN", "Regular"),
        CardItem("Cashalot", 0.02, "BYN", "Regular"),
        CardItem( "МТ", 158.23, "BYN", "ServiceMT"),
        CardItem( "Резерв", 71.0, "BYN", "Reserve"),
        CardItem( "Наличные USD", 2900.0, "USD", "Saving"),
        CardItem( "Мелочь USD", 335.0, "USD", "Saving"),
        CardItem( "Наличные EUR", 980.0, "EUR", "Saving"),
        CardItem( "FinStore Инвестиции", 640.0, "USD", "Saving"),
        CardItem( "FinStore Доход", 12.49, "USD", "Saving"),
        CardItem( "Отложенные BYN", 545.0, "BYN", "Saving"),
        CardItem( "USD на карте", 1000.00, "USD", "Saving")
    )
}