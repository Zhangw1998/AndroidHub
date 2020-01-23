package com.example.androidhub.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    protected val TAG = this.javaClass.simpleName

    /**
     * 布局ID
     */
    abstract fun layoutId(): Int

    /**
     * 初始化View
     */
    abstract fun initView()

    /**
     * 初始化点击事件
     */
    abstract fun initClick()

    /**
     * 初始化数据
     */
    abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initData()
        initView()
        initClick()
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (currentFocus != null) {
            imm?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    /**
     * 显示软键盘
     */
    fun showSoftKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        if (currentFocus != null) {
            imm?.showSoftInput(view, 0)
        }
    }

}