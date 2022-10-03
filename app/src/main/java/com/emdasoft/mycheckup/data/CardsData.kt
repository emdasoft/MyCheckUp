package com.emdasoft.mycheckup.data

import com.emdasoft.mycheckup.domain.CardItem

// временный поставщик данных для дальнейшего проектирования приложения
object CardsData {

    fun getCardsList() = mutableListOf(
        CardItem("Cashalot", 0.00, "BYN", "POV"),
        CardItem("БелВЭБ", 27.03, "BYN", "POV"),
        CardItem("Наличные BYN", 2.50, "BYN", "POV"),
        CardItem( "Резерв", 400.0, "BYN", "RES"),
        CardItem( "МТ", 107.31, "BYN", "MT"),
        CardItem( "Наличные USD", 2950.0, "USD", "SEB"),
        CardItem( "Мелочь USD", 350.0, "USD", "SEB"),
        CardItem( "Наличные EUR", 980.0, "EUR", "SEB"),
        CardItem( "FinStore Инвестиции", 540.0, "USD", "SEB"),
        CardItem( "FinStore Доход", 1.52, "USD", "SEB"),
        CardItem( "Отложенные BYN", 500.0, "BYN", "SEB"),
        CardItem( "USD на карте", 518.19, "USD", "SEB")
    )
}