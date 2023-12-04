package com.ryu.androidstorage.layouts

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.core.content.ContextCompat
import com.ryu.androidstorage.R
import com.ryu.androidstorage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        var button1 = findViewById<Button>(R.id.button)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        button1.setOnClickListener {

        }

        binding.button.setOnClickListener {
            binding.textView.visibility = View.VISIBLE
        }
        binding.button2.setOnClickListener {
            binding.textView2.visibility = View.INVISIBLE
        }

        val cameraPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
        if (cameraPermission == PackageManager.PERMISSION_GRANTED) {
            // 카메라 권한이 승인된 상태일 경우
        } else {
            // 카메라 권한이 승인되지 않았을 경우
        }

        var button2 = findViewById<Button>(R.id.button2)
        var checkbox = findViewById<CheckBox>(R.id.checkBox)

        checkbox.setOnCheckedChangeListener{ compoundButton, b ->
            if(checkbox.isChecked == true){
                button2.visibility = android.view.View.VISIBLE
            } else{
                button2.visibility = View.INVISIBLE
            }
        }

        var radio = findViewById<RadioGroup>(R.id.radioGroup)

        button2.setOnClickListener {
            when(radio.checkedRadioButtonId){
                R.id.radioButton -> 1
            }
        }

        var num1 = "1"
        var num2 : Int
        num2 = num1.toInt()
    }
}