package com.richkart.android.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.richkart.android.DrawerActivity
import com.richkart.android.R
import com.richkart.android.shared_preference.CocoPreferences
import com.richkart.android.signup.SignupActivity
import com.richkart.android.utility.Util.*
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {


    private lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter(this)
        init()
    }

    fun init(){
        btn_register.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        txtFwdPassword.setOnClickListener(this)
    }

    override fun showWait() {
        showProDialog(this)
    }

    override fun removeWait() {
        dismissProDialog()
    }

    override fun onFailure(appErrorMessage: String?) {
        showCenteredToast(clParent, this, appErrorMessage, "")
    }

    override fun onLoginSuccess(loginResponse: LoginResponse?) {
        try {
            if (loginResponse != null) {
                if (!TextUtils.isEmpty(loginResponse.mStatus) && ("1".equals(loginResponse.mStatus))) {

                    CocoPreferences.setUserId(if (TextUtils.isEmpty(loginResponse.loginData!!.mId)) "" else loginResponse.loginData!!.mId)
                    CocoPreferences.setUserEmail(if (TextUtils.isEmpty(loginResponse.loginData!!.mEmail)) "" else loginResponse.loginData!!.mEmail)
                    CocoPreferences.setUserPhone(if (TextUtils.isEmpty(loginResponse.loginData!!.mMobileNo)) "" else loginResponse.loginData!!.mMobileNo)
                    CocoPreferences.setFirstName(if (TextUtils.isEmpty(loginResponse.loginData!!.mName)) "" else loginResponse.loginData!!.mName)
                    CocoPreferences.setLastName(if (TextUtils.isEmpty(loginResponse.loginData!!.mLastName)) "" else loginResponse.loginData!!.mLastName)
                    CocoPreferences.setProfilePic(if (TextUtils.isEmpty(loginResponse.loginData!!.mProfilePic)) "" else loginResponse.loginData!!.mProfilePic)
                    CocoPreferences.savePreferencese()

                    startActivity(Intent(this@LoginActivity, DrawerActivity::class.java))
                    finish()
                } else {
                    showCenteredToast(clParent,this, loginResponse.message,"")
                }

            }
        } catch (e : Exception) {
            e.printStackTrace()
        }

    }

    override fun forgotPassword(forgotPasswordResponse: ForgotPasswordResponse?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClick(view: View?) = try {
        var vId = view?.id
        when (vId) {
            R.id.btn_register -> {
                startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            }
            R.id.btn_login -> {

                var emailId = etEmail.getText().toString().trim()
                var password = etPassword.getText().toString().trim()
                try {
                    if (isValid(emailId, password)) {
                        presenter.Login(emailId, password)
                    } else {

                    }
                } catch (e: Exception) {
                    e.printStackTrace();
                }
            }
            R.id.txtFwdPassword -> {
                startActivity(Intent(this@LoginActivity, ForgotPasswordActivity::class.java))
            }
            else -> {

            }
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }

    @Throws(Exception::class)
    private fun isValid(email: String, password: String): Boolean {
        var validation_detials_flag = false
        if (isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(clParent, this, getString(R.string.email_validation_message), "")
                etEmail.requestFocus()
            } else if (TextUtils.isEmpty(password)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_password), "")
                etPassword.requestFocus()
            } else {
                if (!isEmailValid(email)) {
                    showCenteredToast(clParent, this, getString(R.string.invalid_email), "")
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