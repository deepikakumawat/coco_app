package com.ws.design.coco_ecommerce_ui_kit.shared_preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.ws.design.coco_ecommerce_ui_kit.login.LoginResponse

object CocoPreferences {

   private var mContext: Context? = null
   private var preferences: SharedPreferences? = null
   private var editor: SharedPreferences.Editor? = null
   private val PREFERENCES_NAME: String = "test_login_preference"
   private val KEY_USERID: String = "UserID"
    private val KEY_USEREMAIL: String = "UserEmail"
   private val KEY_USERPHONE: String = "UserPhone"
   private val KEY_FIRST_NAME: String = "FirstName"
   private val KEY_LAST_NAME: String = "LastName"

    @SuppressLint("CommitPrefEdits")
    fun init(context: Context) {
        mContext = context
        preferences = mContext!!.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        editor = preferences!!.edit()
    }

    @JvmStatic
    fun getUserId(): String? {
        return preferences!!.getString(KEY_USERID, "")
    }

    @JvmStatic
    fun setUserId(userId: String) {
        editor!!.putString(KEY_USERID, userId)
    }

    @JvmStatic
    fun getUserEmail(): String? {
        return preferences!!.getString(KEY_USEREMAIL, "")
    }

    @JvmStatic
    fun setUserEmail(userEmail: String) {
        editor!!.putString(KEY_USEREMAIL, userEmail)
    }


    @JvmStatic
    fun getUserPhone(): String? {
        return preferences!!.getString(KEY_USERPHONE, "")
    }

    @JvmStatic
    fun setUserPhone(phone: String) {
        editor!!.putString(KEY_USERPHONE, phone)
    }

    @JvmStatic
    fun getFirstName(): String {
        return preferences!!.getString(KEY_FIRST_NAME, "")
    }

    @JvmStatic
    fun setFirstName(fName: String) {
        editor!!.putString(KEY_FIRST_NAME, fName)
    }


    @JvmStatic
    fun getLastName(): String {
        return preferences!!.getString(KEY_LAST_NAME, "")
    }

    @JvmStatic
    fun setLastName(lname: String) {
        editor!!.putString(KEY_LAST_NAME, lname)
    }


    @JvmStatic
    fun savePreferencese() {
        editor!!.commit()
    }

    @JvmStatic
    fun removeValueForKey(key: String) {
        if (key != null && !key.isEmpty()) {
            editor!!.remove(key)
            editor!!.commit()
        }
    }

    fun clearPreferences() {
        editor!!.clear()
        savePreferencese()
    }

}