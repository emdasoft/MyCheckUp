package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.FragmentAddCardBinding
import com.emdasoft.mycheckup.domain.CardItem

class AddCardFragment : Fragment() {

    private lateinit var binding: FragmentAddCardBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddCardBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currencies = resources.getStringArray(R.array.currency)
        val categories = resources.getStringArray(R.array.category)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, currencies)
        val arrayAdapter2 = ArrayAdapter(requireContext(), R.layout.dropdown_item, categories)

        binding.apply {

            autoCompleteCurrency.setAdapter(arrayAdapter)
            autoCompleteCategory.setAdapter(arrayAdapter2)

            addCardButton.setOnClickListener {
                try {

                    val card = CardItem(
                        labelTextInput.text.toString(),
                        startAmountTextInput.text.toString().toDouble(),
                        autoCompleteCurrency.text.toString(),
                        autoCompleteCategory.text.toString()
                    )

                    dataModel.addCardItem(
                        card
                    )


                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        ?.detach(this@AddCardFragment)
                        ?.replace(R.id.topPlaceHolder, CardsFragment.newInstance())
                        ?.replace(R.id.bottomPlaceHolder, CategoryFragment.newInstance())
                        ?.commit()

                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "Data is incorrectly", Toast.LENGTH_SHORT)
                        .show()

                }
                DismissKeyboard().dismissKeyboard(activity as MainActivity)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = AddCardFragment()
    }
}