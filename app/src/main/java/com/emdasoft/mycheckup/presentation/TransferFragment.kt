package com.emdasoft.mycheckup.presentation

import android.R
import android.R.attr.data
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.emdasoft.mycheckup.DataModel
import com.emdasoft.mycheckup.databinding.FragmentTransferBinding
import com.emdasoft.mycheckup.domain.Card
import com.emdasoft.mycheckup.domain.TransferMoneyUseCase


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

        var cards: ArrayList<Card> = ArrayList()
//        var titles: MutableList<String> = ArrayList<String>()

        dataModel.cardsList.observe(activity as LifecycleOwner) {
            cards = it
        }

//        for (card in cards) {
//            titles.add(card.title)
//        }

        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, cards)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        val adapter2 = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, cards)
        adapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.spinnerSource.adapter = adapter
        binding.spinnerTarget.adapter = adapter2
        var cardSource: Card = cards[0]
        var cardTarget: Card = cards[0]

        binding.spinnerSource.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                cardSource = cards[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }


        binding.spinnerTarget.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
                cardTarget = cards[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        binding.transferButton.setOnClickListener {
            val amount = 10.0


            TransferMoneyUseCase().transferMoney(amount, cardSource, cardTarget)
            println("${cardSource.amount} , ${cardTarget.amount}")
        }




        }


    companion object {
        @JvmStatic
        fun newInstance() = TransferFragment()
    }
}
