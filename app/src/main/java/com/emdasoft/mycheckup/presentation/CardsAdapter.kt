package com.emdasoft.mycheckup.presentation

import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.NewCardItemBinding
import com.emdasoft.mycheckup.domain.CardItem
import kotlin.math.roundToInt

class CardsAdapter(private val listener: OnClickListener, private val metrics: DisplayMetrics) :
    RecyclerView.Adapter<CardsAdapter.CardHolder>() {

    private var count = 0

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

    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = NewCardItemBinding.bind(itemView)
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
            delButton.setOnClickListener {
                listener.onClick(card)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        Log.d("MyAdapter", "${++count}")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.new_card_item, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        var currentItemWidth = itemWidth
        if (position == 0) {
            currentItemWidth += itemMargin
            holder.itemView.setPadding(itemMargin, 0, 0, 0)
        } else if (position == cards.size - 1) {
            currentItemWidth += itemMargin
            holder.itemView.setPadding(0, 0, itemMargin, 0)
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


    override fun getItemViewType(position: Int): Int {

        //TODO("Replace this shit!")
        return position
    }

    companion object {
        const val MAX_POOL_SIZE = 30
        const val DEFAULT_VIEW_TYPE = 0
        const val FIRST_POSITION_VIEW_TYPE = 1

    }


}