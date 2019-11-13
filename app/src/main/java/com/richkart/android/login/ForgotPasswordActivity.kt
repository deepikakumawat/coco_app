package com.richkart.android.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.richkart.android.DrawerActivity
import com.richkart.android.R
import com.richkart.android.shared_preference.CocoPreferences
import com.richkart.android.utility.Util.*
import kotlinx.android.synthetic.main.activity_forgot_password.*
import kotlinx.android.synthetic.main.toolbar_address_list.*
import java.lang.Exception

class ForgotPasswordActivity : AppCompatActivity(), LoginView, View.OnClickListener {


    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        presenter = LoginPresenter(this)

        imgBack.setOnClickListener(this)
        btnSend.setOnClickListener(this)
    }

    override fun showWait() {
        showProDialog(this)
    }

    override fun removeWait() {
        dismissProDialog()
    }


    override fun onFailure(appErrorMessage: String) {
        showCenteredToast(ryParent, this, appErrorMessage, "")
        lyConfirmationMail.visibility = View.GONE
    }

    override fun onLoginSuccess(loginResponse: LoginResponse) {
        try {

            if (loginResponse != null) {
                if (!TextUtils.isEmpty(loginResponse.mStatus) && "1".equals(loginResponse.mStatus, ignoreCase = true)) {

                    CocoPreferences.setUserId(loginResponse.loginData!!.mId)
                    CocoPreferences.setUserEmail(loginResponse.loginData!!.mEmail)
                    CocoPreferences.setUserPhone(loginResponse.loginData!!.mMobileNo)
                    CocoPreferences.setFirstName(loginResponse.loginData!!.mName)
                    CocoPreferences.setLastName(loginResponse.loginData!!.mLastName)
                    CocoPreferences.savePreferencese()

                    val intent = Intent(this@ForgotPasswordActivity, DrawerActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showCenteredToast(ryParent, this, loginResponse.message, "")
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun forgotPassword(forgotPasswordResponse: ForgotPasswordResponse) {
        try {

            if (forgotPasswordResponse != null) {
                lyConfirmationMail.visibility = View.VISIBLE
                etEmail.setText("")
            } else {
                lyConfirmationMail.visibility = View.GONE
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    override fun onClick(view: View?) {
        try {
            var vId = view?.id
            when (vId) {
                R.id.btnSend -> {
                    var emailId = etEmail.getText().toString().trim()
                    try {
                        if (isValid(emailId)) {
                            presenter.forgotPassword(emailId)
                        } else {

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                R.id.imgBack -> {
                    finish()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @Throws(Exception::class)
    private fun isValid(email: String): Boolean {
        var validation_detials_flag = false
        if (isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(ryParent, this, getString(R.string.email_validation_message), "")
                etEmail.requestFocus()
            } else {
                if (!isEmailValid(email)) {
                    showCenteredToast(ryParent, this, getString(R.string.invalid_email), "")
                    etEmail.requestFocus()
                } else {
                    validation_detials_flag = true
                }

            }
        } else {
            showNoInternetDialog(this)
        }
        return validation_detials_flag
    }
}