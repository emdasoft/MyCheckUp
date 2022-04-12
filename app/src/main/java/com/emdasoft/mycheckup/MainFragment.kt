package com.emdasoft.mycheckup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.emdasoft.mycheckup.databinding.FragmentMainBinding

class MainFragment : Fragment() {

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
        binding.btnGo.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.placeHolder, ResultFragment.newInstance())
                ?.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                ?.commit()
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}