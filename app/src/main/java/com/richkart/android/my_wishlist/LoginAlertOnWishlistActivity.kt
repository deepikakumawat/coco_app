package com.richkart.android.my_wishlist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.richkart.android.R
import com.richkart.android.login.LoginActivity
import kotlinx.android.synthetic.main.login_alert_wishlist.*

class LoginAlertOnWishlistActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_alert_wishlist)
        init()
    }

    fun init() {
        btnLogin.setOnClickListener(this)
        txtContinueShopping.setOnClickListener(this)

    }
    override fun onClick(view: View?) {
        try{
            var vId = view?.id
            when (vId){
                R.id.btnLogin -> {
                    var intent = Intent (this@LoginAlertOnWishlistActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.txtContinueShopping -> {
                    finish()
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }

    }
}