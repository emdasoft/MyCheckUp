package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.FragmentCardsBinding
import com.emdasoft.mycheckup.domain.CardItem

class CardsFragment : Fragment(), CardsAdapter.OnClickListener {

    private lateinit var binding: FragmentCardsBinding
    private lateinit var rvPagerAdapter: CardsAdapter
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

            setupRecyclerViewPager()

            dataModel.cardsList.observe(activity as LifecycleOwner) { list ->
                rvPagerAdapter.cards = list
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

    private fun setupRecyclerViewPager() {

        binding.rvPager.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPager.setHasFixedSize(true)
        rvPagerAdapter = CardsAdapter(this, getDisplayMetrics())
        binding.rvPager.adapter = rvPagerAdapter

        rvPagerAdapter.setItemMargin(resources.getDimension(R.dimen.pager_margin).toInt())
        rvPagerAdapter.updateDisplayMetrics()
        PagerSnapHelper().attachToRecyclerView(binding.rvPager)

        setupOnSwipeListener()

    }

    override fun onClick(card: CardItem) {
        dataModel.removeCardItem(card)
        Toast.makeText(requireContext(), "The ${card.title} removed", Toast.LENGTH_SHORT).show()
    }

    private fun getDisplayMetrics(): DisplayMetrics {
        return resources.displayMetrics
    }

    private fun setupOnSwipeListener() {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.UP or ItemTouchHelper.DOWN
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = rvPagerAdapter.cards[viewHolder.adapterPosition]
                dataModel.removeCardItem(item)
            }

        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvPager)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CardsFragment()
    }

}

