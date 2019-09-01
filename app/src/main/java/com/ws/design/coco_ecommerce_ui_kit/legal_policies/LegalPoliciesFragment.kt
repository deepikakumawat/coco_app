package com.ws.design.coco_ecommerce_ui_kit.legal_policies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment
import fragment.FragmentManagerUtils
import kotlinx.android.synthetic.main.fragment_legal_policies.*
import kotlinx.android.synthetic.main.fragment_legal_policies.view.*
import kotlinx.android.synthetic.main.fragment_legal_policies.view.txtPaymentSecurity
import kotlinx.android.synthetic.main.fragment_legal_policies.view.txtPrivacyPolicy
import kotlinx.android.synthetic.main.fragment_legal_policies.view.txtReturningPolicy
import kotlinx.android.synthetic.main.fragment_legal_policies.view.txtShipping
import kotlinx.android.synthetic.main.fragment_legal_policies.view.txtTermsConditions

class LegalPoliciesFragment : BaseFragment(), View.OnClickListener {


    private var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        mView = inflater.inflate(R.layout.fragment_legal_policies, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        txtPrivacyPolicy.setOnClickListener(this)
        txtTermsConditions.setOnClickListener(this)
        txtReturningPolicy.setOnClickListener(this)
        txtPaymentSecurity.setOnClickListener(this)
        txtShipping.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        try {
            var webUrl: String
            var vId = view?.id
            when (vId) {
                R.id.txtPrivacyPolicy -> {
                    webUrl = "https://www.richkart.com/privacy-policy"
                    openWebView(webUrl)
                }
                R.id.txtTermsConditions -> {
                    webUrl = "https://www.richkart.com/terms-conditions"
                    openWebView(webUrl)
                }
                R.id.txtReturningPolicy -> {
                    webUrl = "https://www.richkart.com/return-policy"
                    openWebView(webUrl)
                }
                R.id.txtPaymentSecurity -> {
                    webUrl = "https://www.richkart.com/payment-security"
                    openWebView(webUrl)
                }
                R.id.txtShipping -> {
                    webUrl = "https://www.richkart.com/shipping"
                    openWebView(webUrl)
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun isCartIconVisible(): Boolean {
        return false
    }

    override fun isSearchIconVisible(): Boolean {
        return false
    }

    override fun getActionbarTitle(): String? {
        return ""
    }

    fun openWebView(webUrl: String) {

        var bundle = Bundle()
        bundle.putString("webUrl", webUrl)
        var legalPoliciesWebPages = LegalPoliciesWebPages()
        legalPoliciesWebPages.arguments = bundle
        FragmentManagerUtils.replaceFragmentInRoot(activity?.supportFragmentManager, legalPoliciesWebPages, null, true, false)

    }
}