package com.emdasoft.mycheckup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.emdasoft.mycheckup.databinding.FragmentMainBinding
import java.lang.Exception

class MainFragment : Fragment() {

    private val dataModel: DataModel by activityViewModels()

    private lateinit var binding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGo.setOnClickListener {
            try {
                dataModel.sebData.value =
                    Check.checkIt(
                        binding.incomeTextInput.text.toString().toDouble(),
                        binding.balanceTextInput.text.toString().toDouble()
                    )[0]
                dataModel.resData.value =
                    Check.checkIt(
                        binding.incomeTextInput.text.toString().toDouble(),
                        binding.balanceTextInput.text.toString().toDouble()
                    )[1]
                dataModel.mtData.value =
                    Check.checkIt(
                        binding.incomeTextInput.text.toString().toDouble(),
                        binding.balanceTextInput.text.toString().toDouble()
                    )[2]
                dataModel.povData.value =
                    Check.checkIt(
                        binding.incomeTextInput.text.toString().toDouble(),
                        binding.balanceTextInput.text.toString().toDouble()
                    )[3]

                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.placeHolder, ResultFragment.newInstance())
                    ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    ?.commit()
            } catch (e: Exception) {
                Toast.makeText(
                    requireContext(),
                    "Данные не введены или введены некорректно!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}