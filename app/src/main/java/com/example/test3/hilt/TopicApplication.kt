package com.example.test3.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TopicApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}