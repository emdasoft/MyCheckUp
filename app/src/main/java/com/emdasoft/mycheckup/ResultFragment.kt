package com.emdasoft.mycheckup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()
    private lateinit var viewPager2: ViewPager2
    private val cardOne = Card(0, "Наличные USD", 3200.0, "USD")
    private val cardTwo = Card(1, "FinStore", 1200.0, "USD")
    private val cardThree = Card(2, "Наличные EUR", 980.0, "EUR")

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
            val sliderItems: MutableList<Card> = ArrayList()
            sliderItems.add(cardOne)
            sliderItems.add(cardTwo)
            sliderItems.add(cardThree)

            viewPager2.adapter = CardsAdapter(sliderItems, viewPager2)

            viewPager2.clipToPadding = false
            viewPager2.clipChildren = false
            viewPager2.offscreenPageLimit = 3
            viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(30))
            compositePageTransformer.addTransformer{ page, position ->
                val r = 1 - kotlin.math.abs(position)
                page.scaleY = 0.85f + r * 0.1f
            }
            viewPager2.setPageTransformer(compositePageTransformer)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment()
    }
}