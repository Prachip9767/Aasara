package com.app.aasara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE

import android.webkit.WebView
import android.widget.ProgressBar

class WebActivity : AppCompatActivity() {

    //private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        //progressBar = findViewById(R.id.progressBar)
        val webView = findViewById<WebView>(R.id.webView)
        val url = intent.getStringExtra("url")
        webView.loadUrl(url!!)
    }
}