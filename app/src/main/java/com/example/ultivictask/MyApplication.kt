package com.example.ultivictask

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    // You can override application lifecycle methods if needed
    override fun onCreate() {
        super.onCreate()


    }
}
