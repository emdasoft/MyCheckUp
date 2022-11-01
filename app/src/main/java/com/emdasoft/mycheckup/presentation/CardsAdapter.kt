package com.emdasoft.mycheckup.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.ItemCardBinding
import com.emdasoft.mycheckup.domain.CardItem
import kotlin.math.roundToInt

class CardsAdapter(private val listener: OnClickListener) :
    RecyclerView.Adapter<CardsAdapter.CardHolder>() {

    var count = 0

    var cards = emptyList<CardItem>()
        set(value) {
            val callback = CardListDiffCallback(cards, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    class CardHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemCardBinding.bind(item)
        private val backgrounds = listOf(
            R.drawable.gradient_apricot, R.drawable.gradient_aqua,
            R.drawable.gradient_flow, R.drawable.gradient_turquoise
        )

        fun bind(card: CardItem, listener: OnClickListener) = with(binding) {
            tvCardLabel.text = card.title
            when (card.category) {
                "Regular" -> {
                    cardItem.setBackgroundResource(backgrounds[0])
                }
                "Reserve" -> {
                    cardItem.setBackgroundResource(backgrounds[1])
                }
                "Saving" -> {
                    cardItem.setBackgroundResource(backgrounds[2])
                }
                "ServiceMT" -> {
                    cardItem.setBackgroundResource(backgrounds[3])
                }
            }
            val amount = ((card.amount * 100).roundToInt() / 100.00).toString()
            tvCardAmount.text = amount
            tvCardCurrency.text = card.currency
//            tvDescription.text = itemView.context.getText(R.string.more_info)
            tvDescription.text = card.category
            del.setOnClickListener {
                listener.onClick(card)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        Log.d("MyAdapter", "${++count}")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(cards[position], listener)
    }

    override fun getItemCount(): Int {
        return cards.size
    }

    interface OnClickListener {
        fun onClick(card: CardItem)
    }


}