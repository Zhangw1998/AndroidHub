package com.example.androidhub.app

import android.app.Application
import android.content.Context

class CommonApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
    }

    companion object {

        lateinit var context: Context

    }
}