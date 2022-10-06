package com.emdasoft.mycheckup.presentation

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.emdasoft.mycheckup.databinding.FragmentSpendBinding
import com.emdasoft.mycheckup.domain.CardItem

class SpendFragment : Fragment() {

    private lateinit var binding: FragmentSpendBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpendBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel.cardsList.observe(activity as LifecycleOwner) {
            binding.spinnerSourceForSpend.adapter =
                ArrayAdapter(requireContext(), R.layout.simple_spinner_item, it)
        }

        dataModel.getCardList()

        binding.spendButton.setOnClickListener {

            try {
//                val amount = binding.receiveTextInput.text.toString().toDouble()
//                val cardSource: CardItem = binding.spinnerSourceForReceive.selectedItem as CardItem

                dataModel.spendMoney(
                    binding.spendTextInput.text.toString().toDouble(),
                    binding.spinnerSourceForSpend.selectedItem as CardItem
                )

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.detach(this)
                    ?.replace(
                        com.emdasoft.mycheckup.R.id.topPlaceHolder,
                        CardsFragment.newInstance()
                    )
                    ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    ?.commit()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Data is incorrectly", Toast.LENGTH_SHORT).show()

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = SpendFragment()
    }
}