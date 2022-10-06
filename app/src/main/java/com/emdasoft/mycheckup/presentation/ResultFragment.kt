package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.emdasoft.mycheckup.databinding.FragmentResultBinding
import com.emdasoft.mycheckup.domain.CardItem

class ResultFragment : Fragment(), CardsAdapter.Listener {

    private val dataModel: DataModel by activityViewModels()
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
        dataModel.budget.observe(activity as LifecycleOwner) {
            binding.tvBudgetSaving.text = buildString {
                append("to Saving: ")
                append(it[0])
                append(" BYN")
            }
            binding.tvBudgetReserve.text = buildString {
                append("to Reserve: ")
                append(it[1])
                append(" BYN")
            }
            binding.tvAmountServiceMT.text = buildString {
                append("to ServiceMT: ")
                append(it[2])
                append(" BYN")
            }
            binding.tvAmountRegular.text = buildString {
                append("to Regular: ")
                append(it[3])
                append(" BYN")
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment()
    }

    override fun onClick(card: CardItem) {
        Toast.makeText(requireContext(), "This is ${card.title}", Toast.LENGTH_SHORT).show()
    }
}