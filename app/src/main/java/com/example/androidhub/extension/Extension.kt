package com.example.androidhub.extension

import android.app.Activity
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Activity
 */
fun Activity.toast(msg: String) {
    if (this.isFinishing) return
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.toast(msgId: Int) {
    if (this.isFinishing) return
    Toast.makeText(this, getText(msgId), Toast.LENGTH_SHORT).show()
}

fun Activity.longToast(msg: String) {
    if (this.isFinishing) return
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun Activity.longToast(msgId: Int) {
    if (this.isFinishing) return
    Toast.makeText(this, getText(msgId), Toast.LENGTH_LONG).show()
}

/**
 * Fragment
 */
fun Fragment.toast(msg: String) {
    val activity = this.requireActivity()
    if (activity.isFinishing) return
    Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(msgId: Int) {
    val activity = this.requireActivity()
    if (activity.isFinishing) return
    Toast.makeText(activity, getText(msgId), Toast.LENGTH_SHORT).show()
}

fun Fragment.longToast(msg: String) {
    val activity = this.requireActivity()
    if (activity.isFinishing) return
    Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
}

fun Fragment.longToast(msgId: Int) {
    val activity = this.requireActivity()
    if (activity.isFinishing) return
    Toast.makeText(activity, getText(msgId), Toast.LENGTH_LONG).show()
}


inline fun View.setOnSingleClickListener(crossinline onClick: () -> Unit, delayMillis: Long = 500) {
    this.setOnClickListener {
        this.isClickable = false
        onClick()
        this.postDelayed({
            this.isClickable = true
        }, delayMillis)
    }
}