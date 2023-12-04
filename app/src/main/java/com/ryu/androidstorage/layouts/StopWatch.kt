package com.ryu.androidstorage.layouts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import com.ryu.androidstorage.databinding.ActivityStopWatchBinding

class StopWatch : AppCompatActivity() {
    var initTime = 0L

    var pauseTime = 0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityStopWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()

            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
            binding.startButton.isEnabled = false
        }
        binding.stopButton.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
            binding.startButton.isEnabled = true
        }
        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
            binding.startButton.isEnabled = true
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode === KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }//키를 (키보드느낌) 누른 순간의 이벤트

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }//키를 떼는 순간



    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                Log.d("ryu", "Tocuh down event : ${event.x}, ${event.rawX}") //x는 뷰 내부 기준으로 한 x이고 rawX는 화면을 기준으로한 좌푯값
            }//화면을 손가락으로 누른 순간
            MotionEvent.ACTION_UP -> {
                Log.d("ryu", "Tocuh UP event")
            }//떼는 순간
            MotionEvent.ACTION_MOVE -> {
                Log.d("ryu", "Tocuhing event")
            }//누른 채로 이동하는 순간
        }

        return super.onTouchEvent(event)
    }
}