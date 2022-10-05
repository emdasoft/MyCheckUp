package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.emdasoft.mycheckup.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            dataModel.categoryBalance.observe(activity as LifecycleOwner) {
                tvAmountSeb.text = buildString {
                    append("Saving: ")
                    append(it[0])
                    append(" USD")
                }
                tvAmountRes.text = buildString {
                    append("Reserve: ")
                    append(it[1])
                    append(" BYN")
                }
                tvAmountMt.text = buildString {
                    append("ServiceMT: ")
                    append(it[2])
                    append(" BYN")
                }
                tvAmountPov.text = buildString {
                    append("Regular: ")
                    append(it[3])
                    append(" BYN")
                }
            }

            dataModel.getCategoryBalance()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = CategoryFragment()
    }
}
