package com.emdasoft.mycheckup.domain

class ShowAllCategories(private val cardsRepository: CardsRepository) {

    fun showAllCategories(cards: ArrayList<Card>): ArrayList<String> {
        return cardsRepository.showAllCategories()

    }

}