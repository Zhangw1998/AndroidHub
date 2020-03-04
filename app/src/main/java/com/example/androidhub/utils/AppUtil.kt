package com.example.androidhub.utils

import android.content.Context
import android.content.pm.PackageManager

object AppUtil {

    fun getApplicationName(context: Context): String {
        return try {
            val packageManager = context.applicationContext.packageManager
            val applicationInfo = packageManager.getApplicationInfo(context.packageName, 0)
            packageManager.getApplicationLabel(applicationInfo) as String
        } catch (e: PackageManager.NameNotFoundException) {
            "AndroidApp"
        }
    }
}