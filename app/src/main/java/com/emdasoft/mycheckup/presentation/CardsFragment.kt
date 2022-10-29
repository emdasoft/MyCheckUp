package com.emdasoft.mycheckup.presentation

import android.os.Bundle
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

class CardsFragment : Fragment() {

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

        setupViewPager()

        dataModel.cardsList.observe(activity as LifecycleOwner) { list ->
            (viewPager2.adapter as CardsAdapter).cards = list
        }

        dataModel.currentBalance.observe(activity as LifecycleOwner) {
            val tmpText = "$ $it"
            binding.tvTotalBalance.text = tmpText
        }

        binding.refreshBalance.setOnClickListener {
            Toast.makeText(requireContext(), "Balance refreshed", Toast.LENGTH_SHORT).show()
        }

        binding.receiveCard.setOnClickListener {
            openFragment(ReceiveFragment.newInstance(), R.id.topPlaceHolder)
        }

        binding.spendCard.setOnClickListener {
            openFragment(SpendFragment.newInstance(), R.id.topPlaceHolder)
        }

        binding.transferCard.setOnClickListener {
            openFragment(TransferFragment.newInstance(), R.id.topPlaceHolder)
        }

    }

    private fun setupViewPager() {

        viewPager2 = binding.viewPager

        viewPager2.adapter = CardsAdapter(viewPager2)

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

        (viewPager2.adapter as CardsAdapter).onLongClickListener = {
            dataModel.removeCardItem(it)
            Toast.makeText(requireContext(), "The ${it.title} removed", Toast.LENGTH_SHORT).show()
        }

        (viewPager2.adapter as CardsAdapter).onClickListener = {
            Toast.makeText(requireContext(), "It is ${it.title}", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = CardsFragment()
    }

}

