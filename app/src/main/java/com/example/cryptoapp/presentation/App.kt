package com.example.cryptoapp.presentation

import android.app.Application
import android.content.Context

class App : Application() {

    /**
     * Every Android app is guaranteed to have exactly one [android.app.Application] instance
     * for the lifetime of the app.
     * But there is no guarantee that the non-static onCreate() will have been called before
     * some static initialization code tries to fetch your Context object.
     * (in some cases null check is required)
     */
    companion object {
        lateinit var mContext: Context
        lateinit var sInstance: App
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        mContext = applicationContext
    }
}
