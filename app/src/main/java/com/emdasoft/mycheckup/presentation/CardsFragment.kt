package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.FragmentCardsBinding
import com.emdasoft.mycheckup.domain.CardItem

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
            var cards: List<CardItem>


            dataModel.cardsList.observe(activity as LifecycleOwner) {
                cards = it
                Log.d("MyLiveData", cards.toString())
                viewPager2.adapter = CardsAdapter(cards, viewPager2, this@CardsFragment)
            }

            dataModel.currentBalance.observe(activity as LifecycleOwner) {
                val tmpText = "$ $it"
                tvTotalBalance.text = tmpText
            }

            dataModel.categoryBalance.observe(activity as LifecycleOwner) {
                tvAmountSeb.text = buildString {
                    append("USD ")
                    append(it[0])
                }
                tvAmountRes.text = buildString {
                    append("BYN ")
                    append(it[1])
                }
                tvAmountMt.text = buildString {
                    append("BYN ")
                    append(it[2])
                }
                tvAmountPov.text = buildString {
                    append("BYN ")
                    append(it[3])
                }
            }

            dataModel.getCardList()
            dataModel.getCurrentBalance()
            dataModel.getCategoryBalance()


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