package com.emdasoft.mycheckup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.emdasoft.mycheckup.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

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
        dataModel.sebData.observe(activity as LifecycleOwner,  {
            binding.sebTV.text = it
        })
        dataModel.resData.observe(activity as LifecycleOwner,  {
            binding.resTV.text = it
        })
        dataModel.mtData.observe(activity as LifecycleOwner,  {
            binding.mtTV.text = it
        })
        dataModel.povData.observe(activity as LifecycleOwner,  {
            binding.povTV.text = it
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultFragment()
    }
}