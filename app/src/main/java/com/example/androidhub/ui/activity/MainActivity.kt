package com.example.androidhub.ui.activity

import com.example.androidhub.R
import com.example.androidhub.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun layoutId(): Int = R.layout.activity_main

    override fun initView() { }

    override fun initAction() {
        btn_exoplayer.setOnClickListener {
            ExoplayerActivity.launch(this)
        }
        btn_videos.setOnClickListener {
            VideoActivity.launch(this)
        }
    }

}
