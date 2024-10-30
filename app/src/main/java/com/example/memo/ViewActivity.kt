package com.example.memo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.memo.databinding.ActivityViewBinding

class ViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBinding // ViewBinding 클래스 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        binding = ActivityViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Intent를 통해 전달된 메모 내용 받기
        val memoContent = intent.getStringExtra("MEMO_CONTENT")

        // 받은 메모 내용을 TextView에 설정
        binding.textView.text = memoContent
    }
}
