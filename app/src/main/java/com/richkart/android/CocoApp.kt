package com.richkart.android

import android.app.Application
import com.richkart.android.shared_preference.CocoPreferences

class CocoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        CocoPreferences.init(this)

    }

}