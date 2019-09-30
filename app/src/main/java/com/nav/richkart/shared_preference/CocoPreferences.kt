package com.nav.richkart.shared_preference

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.nav.richkart.login.LoginResponse

object CocoPreferences {

    private var mContext: Context? = null
    private var preferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null
    private val PREFERENCES_NAME: String = "test_login_preference"
    private val FCM_REFRESH_TOKEN: String = "fcm_referesh_token"
    private val  IS_FIRST_TIME_LAUNCH : String = "IsFirstTimeLaunch"
    private val  IS_NIGHT_MODE : String = "isNightMode"
    private val  TOP_FIVE_SEARCH : String = "TOP_FIVE_SEARCH"


    @SuppressLint("CommitPrefEdits")
    fun init(context: Context) {
        mContext = context
        preferences = mContext!!.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        editor = preferences!!.edit()
    }

    @JvmStatic
    fun getUserId(): String? {
        return preferences!!.getString(LoginResponse().KEY_USERID, "")
    }

    @JvmStatic
    fun setUserId(userId: String) {
        editor!!.putString(LoginResponse().KEY_USERID, userId)
    }

    @JvmStatic
    fun getUserEmail(): String? {
        return preferences!!.getString(LoginResponse().KEY_USEREMAIL, "")
    }

    @JvmStatic
    fun setUserEmail(userEmail: String) {
        editor!!.putString(LoginResponse().KEY_USEREMAIL, userEmail)
    }


    @JvmStatic
    fun getUserPhone(): String? {
        return preferences!!.getString(LoginResponse().KEY_USERPHONE, "")
    }

    @JvmStatic
    fun setUserPhone(phone: String) {
        editor!!.putString(LoginResponse().KEY_USERPHONE, phone)
    }

    @JvmStatic
    fun getFirstName(): String {
        return preferences!!.getString(LoginResponse().KEY_FIRST_NAME, "")
    }

    @JvmStatic
    fun setFirstName(fName: String) {
        editor!!.putString(LoginResponse().KEY_FIRST_NAME, fName)
    }


    @JvmStatic
    fun getLastName(): String {
        return preferences!!.getString(LoginResponse().KEY_LAST_NAME, "")
    }

    @JvmStatic
    fun setLastName(lname: String) {
        editor!!.putString(LoginResponse().KEY_LAST_NAME, lname)
    }

    @JvmStatic
    fun getGender(): String {
        return preferences!!.getString(LoginResponse().KEY_GENDER, "")
    }

    @JvmStatic
    fun setGender(lname: String) {
        editor!!.putString(LoginResponse().KEY_GENDER, lname)
    }

    @JvmStatic
    fun getFCMRefreshToken(): String? {
        return preferences!!.getString(FCM_REFRESH_TOKEN, "")
    }

    @JvmStatic
    fun setFCMRefreshToken(fcmRefreshToken: String) {
        editor!!.putString(FCM_REFRESH_TOKEN, fcmRefreshToken)
    }

    @JvmStatic
    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor!!.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
    }

    @JvmStatic
    fun isFirstTimeLaunch(): Boolean {
        return preferences!!.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }


    @JvmStatic
    fun getProfilePic(): String? {
        return preferences!!.getString(LoginResponse().KEY_USER_PIC, "")
    }

    @JvmStatic
    fun setProfilePic(profilePic: String) {
        editor!!.putString(LoginResponse().KEY_USER_PIC, profilePic)
    }

    @JvmStatic
    fun setNightMode(isFirstTime: Boolean) {
        editor!!.putBoolean(IS_NIGHT_MODE, isFirstTime)
    }

    @JvmStatic
    fun isNightMode(): Boolean {
        return preferences!!.getBoolean(IS_NIGHT_MODE, false)
    }

    @JvmStatic
    fun getTopFiveSearch(): MutableSet<String>? {
        return preferences!!.getStringSet(TOP_FIVE_SEARCH, null)
    }

    @JvmStatic
    fun setTopFiveSearch(topFiveSearch: MutableSet<String>) {
        editor!!.putStringSet(TOP_FIVE_SEARCH, topFiveSearch)
    }


    @JvmStatic
    fun savePreferencese() {
        editor!!.commit()
    }

    @JvmStatic
    fun removeValueForKey(key: String) {
        /*  if (key != null && !key.isEmpty()) {
              editor!!.remove(key)
              editor!!.commit()
          }*/

        if (!key.isEmpty()) {
            editor!!.remove(key)
            editor!!.commit()
        }
    }

    fun clearPreferences() {
        editor!!.clear()
        savePreferencese()
    }

}