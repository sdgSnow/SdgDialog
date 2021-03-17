package com.sdg.sdgdialog

import android.app.Application
import com.sdg.dialoglibrary.utils.SdgUtils

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        SdgUtils.install(this)
    }
}