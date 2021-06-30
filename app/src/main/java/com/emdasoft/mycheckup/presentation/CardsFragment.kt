package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.CardsAdapter
import com.emdasoft.mycheckup.DataModel
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.FragmentCardsBinding
import com.emdasoft.mycheckup.domain.CardItem
import kotlin.math.roundToInt

class CardsFragment : Fragment(), CardsAdapter.Listener {

    private val dataModel: DataModel by activityViewModels()
    private lateinit var binding: FragmentCardsBinding
    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            viewPager2 = viewPager
            val cards: ArrayList<CardItem> = ArrayList()
            cards.add(CardItem( "Cashalot", 0.00, "BYN", "POV"))
            cards.add(CardItem( "БелВЭБ", 27.03, "BYN", "POV"))
            cards.add(CardItem( "Наличные BYN", 2.50, "BYN", "POV"))
            cards.add(CardItem( "Резерв", 400.0, "BYN", "RES"))
            cards.add(CardItem( "МТ", 107.31, "BYN", "MT"))
            cards.add(CardItem( "Наличные USD", 2950.0, "USD", "SEB"))
            cards.add(CardItem( "Мелочь USD", 350.0, "USD", "SEB"))
            cards.add(CardItem( "Наличные EUR", 980.0, "EUR", "SEB"))
            cards.add(CardItem( "FinStore Инвестиции", 540.0, "USD", "SEB"))
            cards.add(CardItem( "FinStore Доход", 1.52, "USD", "SEB"))
            cards.add(CardItem( "Отложенные BYN", 500.0, "BYN", "SEB"))
            cards.add(CardItem( "USD на карте", 518.19, "BYN", "SEB"))

            try {
                dataModel.cardsList.value = cards
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Sorry Error!", Toast.LENGTH_SHORT)
                    .show()
            }


            var total = 0.00
            var pov = 0.00
            var res = 0.00
            var mt = 0.00
            var seb = 0.00


            for (item in cards) {
                if (item.category == "POV") {
                    pov += item.amount
                }
                if (item.category == "RES") {
                    res += item.amount
                }
                if (item.category == "MT") {
                    mt += item.amount
                }
                if (item.category == "SEB") {
                    seb += if(item.currency == "BYN"){
                        item.amount / 2.5
                    } else item.amount
                }
                if (item.currency == "USD") {
                    total += item.amount
                }
                if (item.currency == "BYN") {
                    total += item.amount / 2.5
                }
                if (item.currency == "EUR") {
                    total += item.amount
                }
            }
            total = (total * 100).roundToInt() / 100.00
            val tmpText = "$ $total"
            tvTotalBalance.text = tmpText
            seb = ((seb * 100).roundToInt() / 100).toDouble()
            tvAmountSeb.text = "USD $seb"
            tvAmountRes.text = "BYN $res"
            tvAmountMt.text = "BYN $mt"
            tvAmountPov.text = "BYN $pov"

            viewPager2.adapter = CardsAdapter(cards, viewPager2, this@CardsFragment)

            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.setPadding(128, 16, 128, 16)
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
            compositePageTransformer.addTransformer { page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.8f + r * 0.1f
            }
            viewPager2.setPageTransformer(compositePageTransformer)
        }

        binding.receiveCard.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.placeHolder, MainFragment.newInstance())
                ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                ?.commit()
        }

        binding.spendCard.setOnClickListener {
            Toast.makeText(requireContext(), "Spend My Money", Toast.LENGTH_SHORT).show()
        }

        binding.transferCard.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.placeHolder, TransferFragment.newInstance())
                ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                ?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CardsFragment()
    }

    override fun onClick(card: CardItem) {
        Toast.makeText(requireContext(), "This is ${card.title}", Toast.LENGTH_SHORT).show()
    }
}