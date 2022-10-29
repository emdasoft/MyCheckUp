package com.emdasoft.mycheckup.presentation

import androidx.recyclerview.widget.DiffUtil
import com.emdasoft.mycheckup.domain.CardItem

class CardListDiffCallback(
    private val oldList: List<CardItem>,
    private val newList: List<CardItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]
        return oldListItem.id == newListItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]
        return oldListItem == newListItem
    }
}