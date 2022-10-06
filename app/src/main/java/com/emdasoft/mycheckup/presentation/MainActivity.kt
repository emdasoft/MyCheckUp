package com.emdasoft.mycheckup.presentation

import android.os.Bundle
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
                    openFragment(CardsFragment.newInstance(), R.id.topPlaceHolder)
                    openFragment(CategoryFragment.newInstance(), R.id.bottomPlaceHolder)
                }
                3 -> {
                    openFragment(MainFragment.newInstance(), R.id.topPlaceHolder)
                }
            }
        }
    }
}