package com.nav.richkart

import android.app.Application
import com.nav.richkart.shared_preference.CocoPreferences

class CocoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        CocoPreferences.init(this)

    }

}