package com.example.cryptoapp.presentation.fragment.news

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.UiConstants.ARGUMENT_URL
import com.example.cryptoapp.presentation.viewmodel.home.HomeSharedViewModel
import com.example.cryptoapp.presentation.viewmodel.news.WebViewModel
import com.example.cryptoapp.toolchain.mvvmbase.BaseFragment
import kotlinx.android.synthetic.main.fragment_web.*

class WebFragment : BaseFragment<WebViewModel, HomeSharedViewModel>() {

    override fun getLayoutRes(): Int = R.layout.fragment_web

    @Suppress("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        var url: String? = null
        arguments?.let {
            url = it.getString(ARGUMENT_URL)
        }

        webView.webChromeClient = WebChromeClient()
        // define custom web view client for correct load animation showing
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                progressBar?.let { it.visibility = View.GONE }
                webView?.let { it.visibility = View.VISIBLE }
                super.onPageFinished(view, url)
            }
        }
        webView.clearCache(true)
        webView.clearHistory()
        val settings: WebSettings = webView.settings
        @Suppress("DEPRECATION")
        settings.apply {
            allowFileAccess = false  // prevent loading local files
            setSupportMultipleWindows(false)
            setSupportZoom(true)
            setAppCacheEnabled(false)
            databaseEnabled = false
            saveFormData = false
            savePassword = false
            // JavaScript setting
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            domStorageEnabled = true
            // Geo location setting
            setGeolocationEnabled(false)
        }

        url?.let { webView.loadUrl(it) }
    }

    companion object {
        @JvmStatic
        fun newInstance(url: String) =
            WebFragment().apply {
                arguments = Bundle().apply {
                    putString(ARGUMENT_URL, url)
                }
            }
    }

}
