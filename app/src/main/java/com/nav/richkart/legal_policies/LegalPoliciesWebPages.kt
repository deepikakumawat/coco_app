package com.nav.richkart.legal_policies

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.nav.richkart.R
import com.nav.richkart.base_fragment.BaseFragment
import com.nav.richkart.utility.Util
import kotlinx.android.synthetic.main.fragment_legal_policies_web_view.*

class LegalPoliciesWebPages : BaseFragment(){

    private var mView : View? = null
    private var webUrl : String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        mView = inflater.inflate(R.layout.fragment_legal_policies_web_view, container,false)
        return  mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments!=null) {
            webUrl = arguments!!.getString("webUrl")
        }
        showWebPage()
    }

    private fun showWebPage() {
        if (Util.isDeviceOnline(activity)) {
            webView!!.loadUrl(webUrl)
            webView!!.settings.javaScriptEnabled = true
            webView!!.setWebViewClient(LegalPoliciesWebClient())
        }else{
            Util.showNoInternetDialog(activity)
        }
    }

   inner class LegalPoliciesWebClient : WebViewClient(){
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Util.showProDialog(activity)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view!!.loadUrl(webUrl)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Util.dismissProDialog()
        }
    }

    override fun isSearchIconVisible(): Boolean {
        return false
    }

    override fun isCartIconVisible(): Boolean {
        return false
    }

    override fun getActionbarTitle(): String? {
        return null
    }
}