package com.example.week2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SecurityFragment : Fragment() {

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : SecurityFragment {
            return SecurityFragment()
        }
    }

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "SecurityFragment - onCreate() called")
    }

    // fragment를 안고 있는 activity에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"SecurityFragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "SecurityFragment - onCreateView() called")

        val view = inflater.inflate(R.layout.fragment_security, container, false)

        return view
    }
}