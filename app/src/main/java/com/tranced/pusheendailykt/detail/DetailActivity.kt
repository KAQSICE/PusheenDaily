package com.tranced.pusheendailykt.detail

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.tranced.pusheendailykt.R
import java.io.Serializable

class DetailActivity : AppCompatActivity(), Serializable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        initToolbar()
        initWebView()
    }

    fun initWebView() {
        val webView = findViewById<WebView>(R.id.detail_webview)
        webView.webViewClient = WebViewClient()
        webView.loadUrl(intent.getSerializableExtra("newsUrl") as String)
    }

    fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.detail_toolbar)
        setSupportActionBar(toolbar)
        layoutInflater.inflate(R.layout.detail_toolbar, toolbar)
        val backButton = findViewById<ImageView>(R.id.detail_back_button)
        backButton.setOnClickListener { View.OnClickListener { finish() } }
    }
}
