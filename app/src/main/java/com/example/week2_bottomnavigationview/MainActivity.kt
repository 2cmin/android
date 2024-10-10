package com.example.week2

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnItemSelectedListener {

    // 멤버 변수 선언
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var listFragment: ListFragment
    private lateinit var profileFragment: ProfileFragment
    private lateinit var securityFragment: SecurityFragment

    companion object {
        const val TAG: String = "로그"
    }

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 레이아웃과 연결
        setContentView(R.layout.activity_main)

        Log.d(TAG, "MainActivity - onCreate() called")

        // bottom_nav를 findViewById로 참조
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener(this)

        // 초기 프래그먼트 설정
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, homeFragment).commit()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d(TAG, "MainActivity - onNavigationItemSelected() called ")

        // FragmentTransaction 시작
        val transaction = supportFragmentManager.beginTransaction()

        // 슬라이드 애니메이션 적용
        transaction.setCustomAnimations(
            R.anim.slide_in_left, // enter 애니메이션
            R.anim.slide_out_right // exit 애니메이션
        )

        when (item.itemId) {
            R.id.menu_home -> {
                Log.d(TAG, "MainActivity - 홈 버튼 클릭!")
                homeFragment = HomeFragment.newInstance()
                transaction.replace(R.id.fragments_frame, homeFragment)
            }

            R.id.menu_search -> {
                Log.d(TAG, "MainActivity - 검색 버튼 클릭!")
                searchFragment = SearchFragment.newInstance()
                transaction.replace(R.id.fragments_frame, searchFragment)
            }

            R.id.menu_list -> {
                Log.d(TAG, "MainActivity - 목록 버튼 클릭!")
                listFragment = ListFragment.newInstance()
                transaction.replace(R.id.fragments_frame, listFragment)
            }

            R.id.menu_profile -> {
                Log.d(TAG, "MainActivity - 프로필 버튼 클릭!")
                profileFragment = ProfileFragment.newInstance()
                transaction.replace(R.id.fragments_frame, profileFragment)
            }

            R.id.menu_security -> {
                Log.d(TAG, "MainActivity - 보안 버튼 클릭!")
                securityFragment = SecurityFragment.newInstance()
                transaction.replace(R.id.fragments_frame, securityFragment)
            }
        }

        // transaction commit을 마지막에 한 번만 호출
        transaction.commit()
        return true
    }
}
