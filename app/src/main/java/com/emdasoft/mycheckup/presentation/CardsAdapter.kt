package com.emdasoft.mycheckup.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.ItemCardBinding
import com.emdasoft.mycheckup.domain.CardItem
import kotlin.math.roundToInt

class CardsAdapter(
    viewPager: ViewPager2
) : RecyclerView.Adapter<CardsAdapter.CardHolder>() {

    var cards = listOf<CardItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
//            val callback = CardListDiffCallback(cards, value)
//            val diffResult = DiffUtil.calculateDiff(callback)
//            diffResult.dispatchUpdatesTo(this)
            field = value
            notifyDataSetChanged()
        }

    var onLongClickListener: ((CardItem) -> Unit)? = null
    var onClickListener: ((CardItem) -> Unit)? = null

    var count = 0

    class CardHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemCardBinding.bind(item)
        private val backgrounds = listOf(
            R.drawable.gradient_apricot, R.drawable.gradient_aqua,
            R.drawable.gradient_flow, R.drawable.gradient_turquoise
        )

        fun bind(card: CardItem) = with(binding) {
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
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        Log.d("MyAdapter", "${++count}")
        holder.bind(cards[position])
        holder.itemView.setOnLongClickListener {
            onLongClickListener?.invoke(cards[position])
            true
        }
        holder.itemView.setOnClickListener {
            onClickListener?.invoke(cards[position])
        }
    }

    override fun getItemCount(): Int {
        return cards.size
    }

}