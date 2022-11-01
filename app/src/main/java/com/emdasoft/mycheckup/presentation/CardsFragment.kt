package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.FragmentCardsBinding
import com.emdasoft.mycheckup.domain.CardItem
import kotlin.math.abs

class CardsFragment : Fragment(), CardsAdapter.OnClickListener {

    private lateinit var binding: FragmentCardsBinding
    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPagerAdapter: CardsAdapter
    private lateinit var dataModel: DataModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel = ViewModelProvider(this)[DataModel::class.java]

        binding.apply {

            setupViewPager()

            dataModel.cardsList.observe(activity as LifecycleOwner) { list ->
                viewPagerAdapter.cards = list
            }

            dataModel.currentBalance.observe(activity as LifecycleOwner) {
                val tmpText = "$ $it"
                tvTotalBalance.text = tmpText
            }

            dataModel.categoryBalance.observe(activity as LifecycleOwner) { list ->
                tvAmountSeb.text = buildString {
                    append("Saving: ")
                    append(list[0])
                    append(" USD")
                }
                tvAmountRes.text = buildString {
                    append("Reserve: ")
                    append(list[1])
                    append(" BYN")
                }
                tvAmountMt.text = buildString {
                    append("ServiceMT: ")
                    append(list[2])
                    append(" BYN")
                }
                tvAmountPov.text = buildString {
                    append("Regular: ")
                    append(list[3])
                    append(" BYN")
                }
            }

            dataModel.getCategoryBalance()
        }

        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        binding.refreshBalance.setOnClickListener {
            Toast.makeText(requireContext(), "Balance refreshed", Toast.LENGTH_SHORT).show()
        }

        binding.receiveCard.setOnClickListener {
            openFragment(ReceiveFragment.newInstance(), R.id.mainPlaceHolder)
        }

        binding.spendCard.setOnClickListener {
            openFragment(SpendFragment.newInstance(), R.id.mainPlaceHolder)
        }

        binding.transferCard.setOnClickListener {
            openFragment(TransferFragment.newInstance(), R.id.mainPlaceHolder)
        }
    }

    private fun setupViewPager() {

        viewPager2 = binding.viewPager
        viewPagerAdapter = CardsAdapter(this)
        viewPager2.adapter = viewPagerAdapter

        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.setPadding(128, 16, 128, 16)
        viewPager2.offscreenPageLimit = 3
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(30))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - (abs(position) / 48)
            page.scaleY = 0.8f + r * 0.1f
        }
        viewPager2.setPageTransformer(compositePageTransformer)

    }

    companion object {
        @JvmStatic
        fun newInstance() = CardsFragment()
    }

    override fun onClick(card: CardItem) {
        dataModel.removeCardItem(card)
        Toast.makeText(requireContext(), "The ${card.title} removed", Toast.LENGTH_SHORT).show()
    }

}

