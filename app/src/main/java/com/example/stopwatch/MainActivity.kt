package com.example.stopwatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var isRunning = false
    private var time = 0L
    private lateinit var thread: Thread
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding을 사용해 레이아웃을 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Start 버튼 클릭 리스너
        binding.startButton.setOnClickListener {
            if (isRunning) {
                pause()
            } else {
                start()
            }
        }

        // Clear 버튼 클릭 리스너
        binding.clearButton.setOnClickListener {
            clear()
        }
    }

    private fun start() {
        isRunning = true
        binding.startButton.text = "Pause"  // Start -> Pause로 변경
        thread = Thread {
            while (isRunning) {
                Thread.sleep(10)  // 0.01초마다 실행 (밀리초 포함)
                time++
                runOnUiThread {
                    binding.timeTextView.text = formatTime(time)
                }
            }
        }
        thread.start()
    }

    private fun pause() {
        isRunning = false
        binding.startButton.text = "Start"  // Pause -> Start로 변경
    }

    private fun clear() {
        isRunning = false
        time = 0
        binding.timeTextView.text = formatTime(time)
        binding.startButton.text = "Start"  // 버튼을 Start로 초기화
    }

    private fun formatTime(time: Long): String {
        val minutes = time / 6000
        val seconds = (time / 100) % 60
        val milliseconds = time % 100
        return String.format("%02d:%02d,%02d", minutes, seconds, milliseconds)
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false  // Activity 종료 시 Thread도 종료
    }
}
