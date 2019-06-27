package com.ws.design.coco_ecommerce_ui_kit

import android.app.Application
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences

class CocoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        CocoPreferences.init(this)

    }

}