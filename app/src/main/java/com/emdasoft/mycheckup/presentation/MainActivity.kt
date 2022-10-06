package com.emdasoft.mycheckup.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
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

//        binding.bottomNav.setOnItemSelectedListener {
//            when(it){
//                0 -> {
//                    binding.fullPlaceHolder.visibility = View.GONE
//                    binding.topPlaceHolder.visibility = View.VISIBLE
//                    binding.bottomPlaceHolder.visibility = View.VISIBLE
//                    openFragment(CardsFragment.newInstance(), R.id.topPlaceHolder)
//                    openFragment(CategoryFragment.newInstance(), R.id.bottomPlaceHolder)
//                }
//                3 -> {
//                    binding.fullPlaceHolder.visibility = View.VISIBLE
//                    binding.topPlaceHolder.visibility = View.GONE
//                    binding.bottomPlaceHolder.visibility = View.GONE
//                    openFragment(MainFragment.newInstance(), R.id.fullPlaceHolder)
//                }
//            }
//        }


    }


    private fun openFragment(fragment: Fragment, placeholder: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(placeholder, fragment)
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .commit()
    }


}