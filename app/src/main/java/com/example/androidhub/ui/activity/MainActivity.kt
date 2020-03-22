package com.example.androidhub.ui.activity

import android.util.Log
import com.example.androidhub.R
import com.example.androidhub.base.BaseActivity
import com.hoko.blur.HokoBlur
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private val tag = this.javaClass.simpleName

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() { }

    override fun initAction() {
        btn_exoplayer.setOnClickListener {
            ExoplayerActivity.launch(this)
        }
        btn_videos.setOnClickListener {
            VideoActivity.launch(this)
        }
        btn_register.setOnClickListener {
            RegisterActivity.launch(this)
        }
    }
}
