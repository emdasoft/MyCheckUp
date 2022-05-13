package com.emdasoft.mycheckup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.databinding.FragmentResultBinding
import kotlin.math.roundToInt

class ResultFragment : Fragment(), CardsAdapter.Listener {

    private val dataModel: DataModel by activityViewModels()
    private lateinit var viewPager2: ViewPager2
    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataModel.checkData.observe(activity as LifecycleOwner, {
            binding.sebTV.text = it[0]
            binding.resTV.text = it[1]
            binding.mtTV.text = it[2]
            binding.povTV.text = it[3]
        })

        binding.apply {

            viewPager2 = viewPager
            val cards: MutableList<Card> = ArrayList()
            cards.add(Card("Наличные USD", 2550.0, "USD"))
            cards.add(Card("Мелочь USD", 280.0, "USD"))
            cards.add(Card("Наличные EUR", 980.0, "EUR"))
            cards.add(Card("FinStore Инвестиции", 1020.0, "USD"))
            cards.add(Card("FinStore Доход", 22.52, "USD"))
            cards.add(Card("Вклады БелВЭБ", 404.69, "USD"))
            cards.add(Card("Отложенные BYN", 530.92, "BYN"))

            var total = 0.0
            for (item in cards){
                if(item.currency=="USD"){
                    total += item.amount
                }
                if(item.currency=="BYN"){
                    total += (item.amount / 2.5 * 100).roundToInt() / 100.00
                }
                if(item.currency=="EUR"){
                    total += (item.amount * 1.04 * 100).roundToInt() / 100.00
                }
            }
            val tmpText = "Total amount ${total.toString()} USD"
            tvTotal.text = tmpText

            viewPager2.adapter = CardsAdapter(cards, viewPager2, this@ResultFragment)

            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.setPadding(120, 40, 120, 40)
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
            compositePageTransformer.addTransformer{ page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.8f + r * 0.1f
            }
            viewPager2.setPageTransformer(compositePageTransformer)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment()
    }

    override fun onClick(card: Card) {
        Toast.makeText(requireContext(), "This is ${card.title}", Toast.LENGTH_SHORT).show()
    }
}