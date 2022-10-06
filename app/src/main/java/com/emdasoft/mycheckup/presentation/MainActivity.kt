package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.emdasoft.mycheckup.R
import com.emdasoft.mycheckup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFragment(CardsFragment.newInstance(), R.id.topPlaceHolder)
        openFragment(CategoryFragment.newInstance(), R.id.bottomPlaceHolder)

        binding.bottomNav.setOnItemSelectedListener {
            when (it) {
                0 -> {
                    binding.topPlaceHolder.visibility = View.VISIBLE
                    binding.bottomPlaceHolder.visibility = View.VISIBLE
                    binding.fullPlaceHolder.visibility = View.GONE
                    openFragment(CardsFragment.newInstance(), R.id.topPlaceHolder)
                    openFragment(CategoryFragment.newInstance(), R.id.bottomPlaceHolder)
                }
                1 -> {
                    binding.topPlaceHolder.visibility = View.GONE
                    binding.bottomPlaceHolder.visibility = View.GONE
                    binding.fullPlaceHolder.visibility = View.VISIBLE
                    openFragment(AddCardFragment.newInstance(), R.id.fullPlaceHolder)
                }
                3 -> {
                    binding.topPlaceHolder.visibility = View.VISIBLE
                    binding.bottomPlaceHolder.visibility = View.VISIBLE
                    binding.fullPlaceHolder.visibility = View.GONE
                    openFragment(MainFragment.newInstance(), R.id.topPlaceHolder)
                }
            }
        }
    }
}