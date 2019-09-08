package com.nav.richkart.my_cart

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.nav.richkart.R
import com.nav.richkart.login.LoginActivity
import kotlinx.android.synthetic.main.login_alert_on_cart.*
import java.lang.Exception

class LoginAlertOnCartActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_alert_on_cart)
        init()
    }

    fun init() {
        btnLogin.setOnClickListener(this)
        txtContinueShopping.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        try {
            var vId = view?.id
            when (vId) {
                R.id.btnLogin -> {
                    var intent = Intent(this@LoginAlertOnCartActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.txtContinueShopping -> {
                    finish()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}