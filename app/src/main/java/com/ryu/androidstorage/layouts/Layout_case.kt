package com.ryu.androidstorage.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ryu.androidstorage.databinding.ActivityLayoutCaseBinding


class layout_case : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLayoutCaseBinding.inflate(layoutInflater)
        setContentView(binding.button3)

        binding.button3.setOnClickListener {
            binding.button3.visibility = View.INVISIBLE
            binding.imageView2.visibility = View.VISIBLE
        }
        binding.button3.visibility = View.VISIBLE
        binding.imageView2.visibility=View.INVISIBLE

        Toast.makeText(applicationContext, "토스트 연습", Toast.LENGTH_SHORT)
    }
}