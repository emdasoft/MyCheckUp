package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.emdasoft.mycheckup.databinding.FragmentReceiveBinding
import com.emdasoft.mycheckup.domain.CardItem

class ReceiveFragment : Fragment() {

    private lateinit var binding: FragmentReceiveBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReceiveBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel.cardsList.observe(activity as LifecycleOwner) {
            binding.spinnerSourceForReceive.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
        }

        dataModel.getCardList()

        binding.receiveButton.setOnClickListener {

            try {
                dataModel.receiveMoney(
                    binding.receiveTextInput.text.toString().toDouble(),
                    binding.spinnerSourceForReceive.selectedItem as CardItem
                )
                openFragmentWithDetach(
                    CardsFragment.newInstance(),
                    com.emdasoft.mycheckup.R.id.topPlaceHolder
                )

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Data is incorrectly", Toast.LENGTH_SHORT).show()
            }

        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = ReceiveFragment()
    }
}
