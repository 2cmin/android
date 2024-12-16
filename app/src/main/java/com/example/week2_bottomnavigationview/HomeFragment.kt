package com.example.week2

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week2_bottomnavigationview.RecyclerAdapter

class HomeFragment : Fragment() {

    companion object {
        const val TAG : String = "로그"

        fun newInstance() : HomeFragment {
            return HomeFragment()
        }
    }

    // 메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "HomeFragment - onCreate() called")
    }

    // fragment를 안고 있는 activity에 붙었을 때
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"HomeFragment - onAttach() called")
    }

    // 뷰가 생성되었을 때
    // 프래그먼트와 레이아웃을 연결시켜주는 부분
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "HomeFragment - onCreateView() called")

        // fragment_home.xml 파일을 inflate
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // RecyclerView 설정
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = RecyclerAdapter(getDummyData())  // 더미 데이터 전달

        return view
    }

    // 더미 데이터를 생성하는 함수
    private fun getDummyData(): List<Pair<String, Int>> {
        return listOf(
            Pair("공사장 재롱이", R.drawable.image1),
            Pair("곰돌이 재롱이", R.drawable.image2),
            Pair("가을 재롱이", R.drawable.image3),
            Pair("튜브타는 재롱이", R.drawable.image4),
            Pair("재롱이 확대샷", R.drawable.image5),
        )
    }

}