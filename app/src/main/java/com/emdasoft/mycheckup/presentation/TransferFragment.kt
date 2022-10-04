package com.emdasoft.mycheckup.presentation

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.emdasoft.mycheckup.databinding.FragmentTransferBinding
import com.emdasoft.mycheckup.domain.CardItem


class TransferFragment : Fragment() {

    private lateinit var binding: FragmentTransferBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTransferBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var cards: List<CardItem> = ArrayList()


        dataModel.cardsList.observe(activity as LifecycleOwner) {
            cards = it
            binding.spinnerSource.adapter =
                ArrayAdapter(requireContext(), R.layout.simple_spinner_item, cards)
            binding.spinnerTarget.adapter =
                ArrayAdapter(requireContext(), R.layout.simple_spinner_item, cards)
        }

        dataModel.getCardList()

        binding.transferButton.setOnClickListener {
            val amount = 10.0
            var cardSource: CardItem
            var cardTarget: CardItem

            binding.spinnerSource.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    cardSource = cards[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

            binding.spinnerTarget.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    cardTarget = cards[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }


        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = TransferFragment()
    }
}
