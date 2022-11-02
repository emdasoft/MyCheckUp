package com.emdasoft.mycheckup.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.NewCardItemBinding
import com.emdasoft.mycheckup.domain.CardItem
import kotlin.math.roundToInt

class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val binding = NewCardItemBinding.bind(itemView)
    private val backgrounds = listOf(
        R.drawable.gradient_apricot, R.drawable.gradient_aqua,
        R.drawable.gradient_flow, R.drawable.gradient_turquoise
    )

    fun bind(card: CardItem, listener: CardsAdapter.OnClickListener) = with(binding) {
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