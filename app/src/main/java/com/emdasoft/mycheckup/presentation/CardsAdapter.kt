package com.emdasoft.mycheckup.presentation

import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.domain.CardItem

class CardsAdapter(private val listener: OnClickListener, private val metrics: DisplayMetrics) :
    RecyclerView.Adapter<CardHolder>() {

    private var itemMargin: Int = 0
    private var itemWidth: Int = 0

    var cards = emptyList<CardItem>()
        set(value) {
            val callback = CardListDiffCallback(cards, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    fun setItemMargin(itemMargin: Int) {
        this.itemMargin = itemMargin
    }

    fun updateDisplayMetrics() {
        itemWidth = metrics.widthPixels - itemMargin * 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.new_card_item, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var currentItemWidth = itemWidth
        when (position) {
            0 -> {
                currentItemWidth += itemMargin
                holder.itemView.setPadding(itemMargin, 0, 0, 0)
            }
            itemCount - 1 -> {
                currentItemWidth += itemMargin
                holder.itemView.setPadding(0, 0, itemMargin, 0)
            }
            else -> {
                holder.itemView.setPadding(0, 0, 0, 0)
            }
        }

        val height = holder.itemView.layoutParams.height
        holder.itemView.layoutParams = ViewGroup.LayoutParams(currentItemWidth, height)
        holder.bind(cards[position], listener)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    interface OnClickListener {
        fun onClick(card: CardItem)
    }

}