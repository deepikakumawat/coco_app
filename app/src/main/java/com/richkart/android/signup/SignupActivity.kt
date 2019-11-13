package com.richkart.android.signup

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.RadioButton
import com.richkart.android.MobileVerificationActivity
import com.richkart.android.R
import com.richkart.android.utility.Util
import com.richkart.android.utility.Util.*
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity(), View.OnClickListener, SignUpView {


    lateinit var signUpPresenter: SignUpPresenter
    lateinit var email: String
    lateinit var fName: String
    lateinit var lName: String
    lateinit var phone: String
    lateinit var password: String
    lateinit var confirmPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        signUpPresenter = SignUpPresenter(this)
        btnSignup.setOnClickListener(this)

    }

    @Throws(Exception::class)
    private fun isValid(email: String, fName: String, lName: String, phone: String, password: String, confirmPassword: String): Boolean {
        var validation_detials_flag = false
        if (Util.isDeviceOnline(this)) {
            if (TextUtils.isEmpty(email)) {
                showCenteredToast(clParent, this, getString(R.string.email_validation_message), "")
                etEmail.requestFocus()
            } else if (TextUtils.isEmpty(fName)) {
                showCenteredToast(clParent, this, getString(R.string.first_name_validation_message), "")
                etFname.requestFocus()
            } else if (TextUtils.isEmpty(lName)) {
                showCenteredToast(clParent, this, getString(R.string.last_name_validation_message), "")
                etLname.requestFocus()
            } else if (TextUtils.isEmpty(phone)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_mobile_number), "")
                etPhone.requestFocus()
            } else if (TextUtils.isEmpty(password)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_password), "")
                etPassword.requestFocus()
            } else if (TextUtils.isEmpty(confirmPassword)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_password), "")
                etConfirmPassword.requestFocus()
            } else if (!isEmailValid(email)) {
                showCenteredToast(clParent, this, getString(R.string.invalid_email), "")
                etEmail.requestFocus()
            } else if (!isValidMobile(phone)) {
                showCenteredToast(clParent, this, getString(R.string.mobile_number), "")
                etPhone.requestFocus()
            } else if (!confirmPassword.equals(password, ignoreCase = true)) {
                showCenteredToast(clParent, this, getString(R.string.password_confirm_password), "")
                etConfirmPassword.requestFocus()

            } else {
                validation_detials_flag = true
            }
        } else {
            Util.showNoInternetDialog(this)
        }
        return validation_detials_flag
    }

    override fun onClick(view: View?) {
        try {
            var vId = view?.id
            when (vId) {
                R.id.btnSignup -> {
                    email = etEmail.getText().toString().trim()
                    fName = etFname.getText().toString().trim()
                    lName = etLname.getText().toString().trim()
                    phone = etPhone.getText().toString().trim()
                    password = etPassword.getText().toString().trim()
                    confirmPassword = etConfirmPassword.getText().toString().trim()

                    try {
                        if (isValid(email, fName, lName, phone, password, confirmPassword)) {

                            signUpPresenter.getOtp(phone)


                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }


                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun showWait() {
        showProDialog(this)
    }

    override fun removeWait() {
        dismissProDialog()
    }

    override fun onFailure(appErrorMessage: String) {
        showCenteredToast(clParent, this, appErrorMessage, "")
    }

    override fun onSignSuccess(signUpResponse: SignUpResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOTP(getOTPResponse: GetOTPResponse) {
        try {
            showCenteredToast(clParent, this, getOTPResponse.message, "")

            if (getOTPResponse.mOTPData != null) {
                if (!TextUtils.isEmpty(getOTPResponse.mOTPData!!.mVcToken)) {
                    val VCToken = getOTPResponse.mOTPData!!.mVcToken

                    val intent = Intent(this@SignupActivity, MobileVerificationActivity::class.java)
                    val selectedId = rgPayment.checkedRadioButtonId

                    // find the radiobutton by returned id
                    val radioButton = findViewById<View>(selectedId) as RadioButton
                    val bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("fName", fName)
                    bundle.putString("lName", lName)
                    bundle.putString("phone", phone)
                    bundle.putString("password", password)
                    bundle.putString("gender", radioButton.text.toString())
                    bundle.putString("confirmPassword", confirmPassword)
                    bundle.putString("VCToken", VCToken)

                    intent.putExtra("signupdata", bundle)

                    startActivity(intent)

                } else {
                    showCenteredToast(clParent, this, getString(R.string.no_vc_token_found), "")

                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}