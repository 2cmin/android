package com.example.samplelogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kakao.sdk.user.UserApiClient

class LoginActivity : AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.kakao_login_button)
        logoutButton = findViewById(R.id.kakao_logout_button)

        // 로그인 버튼 클릭 시 로그인 시도
        loginButton.setOnClickListener {
            loginWithKakao()
        }

        // 로그아웃 버튼 클릭 시 로그아웃 처리
        logoutButton.setOnClickListener {
            logoutWithKakao()
        }

        // 로그인 상태 확인
        checkLoginStatus()
    }

    // 카카오 로그인 처리
    private fun loginWithKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.e("Login", "카카오톡으로 로그인 실패", error)
                    loginWithKakaoAccount()
                } else if (token != null) {
                    Log.d("Login", "로그인 성공 ${token.accessToken}")
                    Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                    getUserInfo()

                    // MainActivity로 이동
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // LoginActivity 종료
                }
            }
        } else {
            loginWithKakaoAccount()
        }
    }


    // 카카오 계정으로 로그인
    private fun loginWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            if (error != null) {
                Log.e("Login", "카카오 계정으로 로그인 실패", error)
                Toast.makeText(this, "로그인 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            } else if (token != null) {
                Log.d("Login", "로그인 성공 ${token.accessToken}")
                Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show()
                getUserInfo()
                loginButton.visibility = Button.GONE
                logoutButton.visibility = Button.VISIBLE
            }
        }
    }


    // 카카오 사용자 정보 요청
    private fun getUserInfo() {
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("Login", "사용자 정보 요청 실패", error)
            } else if (user != null) {
                // 사용자 정보 출력 (예: 닉네임)
                val nickname = user.kakaoAccount?.profile?.nickname
                Log.d("User Info", "사용자 닉네임: $nickname")
                Toast.makeText(this, "사용자 닉네임: $nickname", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 카카오 로그아웃 처리
    private fun logoutWithKakao() {
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e("Logout", "로그아웃 실패", error)
            } else {
                Log.d("Logout", "로그아웃 성공")
                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()

                // 로그아웃 후 로그인 버튼 보이기, 로그아웃 버튼 숨기기
                loginButton.visibility = Button.VISIBLE
                logoutButton.visibility = Button.GONE
            }
        }
    }

    // 로그인 상태 확인
    private fun checkLoginStatus() {
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                // 로그인이 안 되어 있는 상태
                Log.d("Login Status", "로그인 상태 아님: ${error.message}")
                loginButton.visibility = Button.VISIBLE
                logoutButton.visibility = Button.GONE
            } else if (tokenInfo != null) {
                // 로그인되어 있는 상태
                Log.d("Login Status", "로그인 상태 유지 중, 사용자 ID: ${tokenInfo.id}")
                loginButton.visibility = Button.GONE
                logoutButton.visibility = Button.VISIBLE
            }
        }
    }
}
