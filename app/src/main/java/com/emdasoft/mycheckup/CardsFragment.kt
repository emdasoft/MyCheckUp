package com.emdasoft.mycheckup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.databinding.FragmentCardsBinding
import kotlin.math.roundToInt

class CardsFragment : Fragment(), CardsAdapter.Listener {

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
            val cards: MutableList<Card> = ArrayList()
            cards.add(Card("Cashalot", 52.23, "BYN", "POV"))
            cards.add(Card("БелВЭБ", 156.89, "BYN", "POV"))
            cards.add(Card("Наличные BYN", 96.0, "BYN", "POV"))
            cards.add(Card("Резерв", 400.0, "BYN", "RES"))
            cards.add(Card("МТ", 176.69, "BYN", "MT"))
            cards.add(Card("Наличные USD", 2750.0, "USD", "SEB"))
            cards.add(Card("Мелочь USD", 300.0, "USD", "SEB"))
            cards.add(Card("Наличные EUR", 980.0, "EUR", "SEB"))
            cards.add(Card("FinStore Инвестиции", 1020.0, "USD", "SEB"))
            cards.add(Card("FinStore Доход", 3.52, "USD", "SEB"))
            cards.add(Card("Отложенные BYN", 300.0, "BYN", "SEB"))

            var total = 0.0
            var pov = 0.0
            var res = 0.0
            var mt = 0.0
            var seb = 0.0


            for (item in cards) {
                if (item.description == "POV"){
                    pov += item.amount
                }
                if (item.description == "RES"){
                    res += item.amount
                }
                if (item.description == "MT"){
                    mt += item.amount
                }
                if (item.description == "SEB"){
                    seb += item.amount
                }
                if (item.currency == "USD") {
                    total += item.amount
                }
                if (item.currency == "BYN") {
                    total += item.amount / 2.5
                }
                if (item.currency == "EUR") {
                    total += item.amount * 1.04
                }
            }
            total = (total * 100).roundToInt() / 100.00
            val tmpText = "$ $total"
            tvTotalBalance.text = tmpText

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

        binding.receiveButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.placeHolder, MainFragment.newInstance())
                ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                ?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = CardsFragment()
    }

    override fun onClick(card: Card) {
        Toast.makeText(requireContext(), "This is ${card.title}", Toast.LENGTH_SHORT).show()
    }
}