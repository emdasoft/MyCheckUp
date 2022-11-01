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

        dataModel.cardsList.observe(activity as LifecycleOwner) {
            binding.spinnerSourceForTransfer.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
            binding.spinnerTargetForTransfer.adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, it)
        }


        binding.transferButton.setOnClickListener {

            try {
                dataModel.transferMoney(
                    binding.transferTextInput.text.toString().toDouble(),
                    binding.spinnerSourceForTransfer.selectedItem as CardItem,
                    binding.spinnerTargetForTransfer.selectedItem as CardItem
                )

                openFragmentWithDetach(
                    CardsFragment.newInstance(),
                    com.emdasoft.mycheckup.R.id.mainPlaceHolder,
                )

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Data is incorrectly", Toast.LENGTH_SHORT).show()
            }

        }

    }


    companion object {
        @JvmStatic
        fun newInstance() = TransferFragment()
    }
}
