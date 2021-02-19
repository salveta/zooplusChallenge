package com.salvaperez.airquality

import android.app.Application
import android.content.Context
import com.salvaperez.airquality.data.di.initDI

class AirQualityApplication: Application() {

    private var mContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        initDI()
        mContext = this;
    }

    fun getContext(): Context? {
        return mContext
    }
}