package com.sdg.sdgdialog

import android.app.Application
import com.sdg.dialoglibrary.utils.AppUtils

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppUtils.install(this)
    }
}