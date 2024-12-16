package com.example.samplelogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient

class MainActivity : AppCompatActivity() {

    private lateinit var logoutButton: Button
    private lateinit var userInfoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        logoutButton = findViewById(R.id.logout_button)
        userInfoTextView = findViewById(R.id.user_info_text_view)

        // 로그아웃 버튼 클릭 시 로그아웃 처리
        logoutButton.setOnClickListener {
            logoutWithKakao()
        }

        // 로그인 상태 확인 및 사용자 정보 가져오기
        checkLoginStatus()
    }

    // 카카오 로그아웃 처리
    private fun logoutWithKakao() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Toast.makeText(this, "로그아웃 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()

                // 로그아웃 후 LoginActivity로 이동
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // MainActivity 종료
            }
        }
    }

    // 로그인 상태 확인 및 사용자 정보 가져오기
    private fun checkLoginStatus() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Toast.makeText(this, "로그인 상태 확인 실패", Toast.LENGTH_SHORT).show()
            } else if (user != null) {
                // 사용자 정보 출력 (예: 닉네임)
                val nickname = user.kakaoAccount?.profile?.nickname
                userInfoTextView.text = "사용자 닉네임: $nickname"
            } else {
                // 사용자가 로그인하지 않았다면, LoginActivity로 이동
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish() // MainActivity 종료
            }
        }
    }
}
