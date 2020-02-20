package com.example.androidhub.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

object KeyboardHelper {

    /**
     * 隐藏软键盘
     */
    fun hideSoftKeyboard(view: View?): Boolean {
        if (null == view) return false
        val inputManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun show(et: EditText) {

    }

    fun hide(view: View) {

    }

}