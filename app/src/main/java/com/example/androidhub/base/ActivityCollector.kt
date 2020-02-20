package com.example.androidhub.base

import android.app.Activity

object ActivityCollector {

    private val activities = arrayListOf<Activity>()

    fun addActivity(activity: Activity) {
        activities.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }

    fun finishAll() {
        for (activity in activities) {
            if (activity.isFinishing) {
                activity.finish()
            }
        }
    }

}