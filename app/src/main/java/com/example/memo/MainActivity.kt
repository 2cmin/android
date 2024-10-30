package com.example.memo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var memoContent: String // 메모 내용 저장할 변수
    private lateinit var binding: ActivityMainBinding // ViewBinding 사용

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 설정
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 메모 저장 버튼 클릭 리스너
        binding.btnSave.setOnClickListener {
            memoContent = binding.editText.text.toString()
            if (memoContent.isNotEmpty()) {
                // 메모가 저장되었다는 메시지 표시
                Toast.makeText(this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "메모를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }

        // 확인 화면으로 가는 버튼 클릭 리스너
        binding.btnView.setOnClickListener {
            // 메모 내용을 전달하여 ViewActivity로 전환
            val intent = Intent(this, ViewActivity::class.java)
            intent.putExtra("MEMO_CONTENT", memoContent) // 메모 내용 전달
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // onPause에서 저장된 메모 내용이 있다면 EditText에 설정
        if (::memoContent.isInitialized && memoContent.isNotEmpty()) {
            binding.editText.text = Editable.Factory.getInstance().newEditable(memoContent)
        }
    }

    override fun onPause() {
        super.onPause()
        // 현재 작성 중인 메모 내용을 memoContent 변수에 저장
        memoContent = binding.editText.text.toString()
    }

    override fun onRestart() {
        super.onRestart()
        // 앱이 재시작될 때 다이얼로그를 표시하여 이어서 작성 여부 확인
        val builder = AlertDialog.Builder(this)
        builder.setTitle("메모장 관리자")
            .setMessage("앱으로 돌아왔습니다. 계속 작성하시겠습니까?")
            .setPositiveButton("예") { _, _ ->
                // EditText에 저장된 메모 내용 복원
                binding.editText.text = Editable.Factory.getInstance().newEditable(memoContent)
            }
            .setNegativeButton("아니요") { _, _ ->
                // 메모 내용 비우기
                memoContent = ""
            }
        builder.show()
    }
}
