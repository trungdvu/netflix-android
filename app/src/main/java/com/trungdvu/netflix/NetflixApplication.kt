package com.trungdvu.netflix

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NetflixApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}