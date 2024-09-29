package com.example.week1

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ImageButton들의 ID를 리스트로 관리
        val buttonIds = listOf(
            R.id.happyButton,
            R.id.excitedButton,
            R.id.ordinaryButton,
            R.id.unstableButton,
            R.id.angryButton
        )

        // 각 버튼 클릭 시 Activity2로 화면 전환
        buttonIds.forEach { buttonId ->
            val button = findViewById<ImageButton>(buttonId)
            button.setOnClickListener {
                val intent = Intent(this, Activity2::class.java)
                startActivity(intent)
            }
        }
    }
}
