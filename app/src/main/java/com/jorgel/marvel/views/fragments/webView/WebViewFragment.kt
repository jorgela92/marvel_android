package com.jorgel.marvel.views.fragments.webView

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.jorgel.marvel.R
import com.jorgel.marvel.views.genericViews.CustomLoading
import kotlinx.android.synthetic.main.web_fragment.*

class WebViewFragment(private val url: String): Fragment() {

    companion object {
        fun newInstance(urlS: String) = WebViewFragment(urlS)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.web_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        CustomLoading.getInstance(activity).show()
        configureUI()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun configureUI() {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                CustomLoading.getInstance(activity).hide()
            }
        }
        webView.loadUrl(url.replace("http", "https"))
    }
}