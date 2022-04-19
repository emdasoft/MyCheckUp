package com.emdasoft.mycheckup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.emdasoft.mycheckup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        openFragment(MainFragment.newInstance())
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> openFragment(MainFragment.newInstance())
                R.id.cards -> openFragment(ResultFragment.newInstance())
            }
            true
        }
    }

    private fun openFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(R.anim.from_right_to_left, R.anim.exit_rigth_to_left,
            R.anim.from_left_to_right, R.anim.exit_left_to_right)
            .replace(R.id.placeHolder, f)
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .commit()
    }
}