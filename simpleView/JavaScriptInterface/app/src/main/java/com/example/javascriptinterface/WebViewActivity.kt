package com.example.javascriptinterface

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity : AppCompatActivity() {

    companion object {
        private val TAG = WebViewActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
        setContentView(R.layout.activity_main)
        val webView: WebView = findViewById(R.id.webView)
        //웹뷰의 자바스크립트 기능을 활성화
        webView.settings.javaScriptEnabled = true

        //Android 명의 JavascriptInterface 를 추가
        webView.addJavascriptInterface(WebAppInterface(this), "Android")

        //assets에 있는 sample.html을 로딩합니다.
        webView.loadUrl("file:///android_asset/sample.html")
    }

    /** Instantiate the interface and set the context  */
    class WebAppInterface(private val mContext: Context) {

        /** Show a toast from the web page  */
        @JavascriptInterface
        fun showToast(toast: String) {
            Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show()
        }
    }
}