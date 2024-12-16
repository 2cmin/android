package com.example.samplelogin

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 카카오 SDK 초기화
        KakaoSdk.init(this, "kakao96604a10dd8c798a6e81b1fcb5e2a606")
    }
}
