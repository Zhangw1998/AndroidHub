package com.example.androidhub.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.androidhub.R
import com.example.androidhub.api.ApiModel
import com.example.androidhub.base.BaseActivity
import com.example.androidhub.extension.toast
import com.example.androidhub.ui.adapter.Pager2Adapter
import com.example.androidhub.ui.adapter.Pager2FragmentAdapter
import com.example.androidhub.ui.fragment.Pager2Fragment
import kotlinx.android.synthetic.main.activity_videos.*


class VideoActivity : BaseActivity() {

    private lateinit var adapter: Pager2Adapter

    private lateinit var fragmentAdapter: Pager2FragmentAdapter

    override fun layoutId(): Int {
        return R.layout.activity_videos
    }

    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setFullScreen()
        super.onCreate(savedInstanceState)
    }

    override fun initAction() {
        btn_choose.setOnClickListener {
            AlertDialog.Builder(this)
                .setItems(arrayOf("TestVideo", "VideoData")) { dialog, which ->
                    dialog.cancel()
                    it.visibility = View.GONE
                    when (which) {
                        0 -> fetchVideos1()
                        1 -> fetchVideos2()
                    }
                }.show()
        }


    }

    @SuppressLint("CheckResult")
    fun fetchVideos1() {
        ApiModel.fetVideosInfo()
            .subscribe({
                val fragments = mutableListOf<Fragment>()
                it.forEach { video ->
                    fragments.add(Pager2Fragment.newInstance(video))
                }
                fragmentAdapter = Pager2FragmentAdapter(this, fragments)
                vp2.adapter = fragmentAdapter
            }, {
                toast("Failed to fetch videos")
            })
    }

    @SuppressLint("CheckResult")
    fun fetchVideos2() {
        ApiModel.fetchVideos()
            .subscribe({
                val fragments = mutableListOf<Fragment>()
                it.forEach { video ->
                    fragments.add(Pager2Fragment.newInstance(imageData = video))
                }
                fragmentAdapter = Pager2FragmentAdapter(this, fragments)
                vp2.adapter = fragmentAdapter

            }, {
                toast("Failed to fetch videos")
            })
    }

    private fun setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        val flagsFullScreen = WindowManager.LayoutParams.FLAG_FULLSCREEN
        window.addFlags(flagsFullScreen)
        if (supportActionBar != null) supportActionBar?.hide()
    }

    private fun cancelFullScreen() {
        val attrs = window.attributes
        val flagsFullScreen = WindowManager.LayoutParams.FLAG_FULLSCREEN
        attrs.flags = attrs.flags and flagsFullScreen.inv()
        window.attributes = attrs
        window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        if (supportActionBar != null) supportActionBar?.show() //应用标题栏


    }


    companion object {
        fun launch(ctx: Context) {
            val intent = Intent(ctx, VideoActivity::class.java)
            ctx.startActivity(intent)
        }
    }

}
