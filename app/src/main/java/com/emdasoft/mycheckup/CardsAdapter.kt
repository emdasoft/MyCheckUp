package com.emdasoft.mycheckup

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.databinding.ItemCardBinding

class CardsAdapter internal constructor(
    cards: MutableList<Card>,
    viewPager: ViewPager2
) : RecyclerView.Adapter<CardsAdapter.CardHolder>() {

    private var cards: List<Card> = cards

    class CardHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemCardBinding.bind(item)
        fun bind(card: Card) = with(binding) {
            tvCardLabel.text = card.title
            tvCardAmount.text = card.amount.toString()
            tvCardCurrency.text = card.currency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cards[position])
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}