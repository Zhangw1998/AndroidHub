package com.example.androidhub.extension

import android.app.Activity
import android.view.View
import android.widget.Toast

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
inline fun View.setOnSingleClickListener(crossinline onClick: () -> Unit, delayMillis: Long = 500) {
    this.setOnClickListener {
        this.isClickable = false
        onClick()
        this.postDelayed({
            this.isClickable = true
        }, delayMillis)
    }
}