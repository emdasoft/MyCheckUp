package com.emdasoft.mycheckup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emdasoft.mycheckup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnGo.setOnClickListener{
//            val res = Check.checkIt(1200.0, 10.0)
//            val pov = res[res.size - 1]
//            binding.tv.text = "${res[0]} // ${res[1]} // ${res[2]} // ${res[3]}"
//            Log.d("MyLog", "$res")
//        }
    }


}