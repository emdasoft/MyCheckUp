package com.emdasoft.mycheckup.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.ItemCardBinding
import com.emdasoft.mycheckup.domain.CardItem

class CardsAdapter (
    cards: List<CardItem>,
    viewPager: ViewPager2,
    private val listener: Listener
) : RecyclerView.Adapter<CardsAdapter.CardHolder>() {

    private var cards: List<CardItem> = cards

    class CardHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemCardBinding.bind(item)
        private val backgrounds = listOf(
            R.drawable.gradient_apricot, R.drawable.gradient_aqua,
            R.drawable.gradient_flow, R.drawable.gradient_blue, R.drawable.gradient_pink,
            R.drawable.gradient_cornflower, R.drawable.gradient_seattle,
            R.drawable.gradient_turquoise
        )
        fun bind(card: CardItem, listener: Listener) = with(binding) {
            tvCardLabel.text = card.title
            cardItem.setBackgroundResource(backgrounds.random())
            tvCardAmount.text = card.amount.toString()
            tvCardCurrency.text = card.currency
            tvDescription.text = itemView.context.getText(R.string.more_info)
            itemView.setOnClickListener {
                listener.onClick(card)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cards[position], listener)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    interface Listener {
        fun onClick(card: CardItem)
    }

}