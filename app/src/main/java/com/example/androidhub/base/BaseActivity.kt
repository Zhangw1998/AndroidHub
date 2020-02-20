package com.example.androidhub.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

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
    abstract fun initAction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        initView()
        initAction()
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    companion object {

        protected val TAG = this.javaClass.simpleName

    }

}