package com.example.androidhub.ui.activity

import android.annotation.SuppressLint
import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.viewpager2.widget.ViewPager2
import com.example.androidhub.R
import com.example.androidhub.api.ApiModel
import com.example.androidhub.base.BaseActivity
import com.example.androidhub.ui.adapter.Pager2Adapter
import kotlinx.android.synthetic.main.activity_videos.*

class VideoActivity : BaseActivity() {

    private lateinit var adapter: Pager2Adapter

    override fun layoutId(): Int {
        return R.layout.activity_videos
    }

    override fun initView() {
        vp2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
            }
        })
    }

    @SuppressLint("CheckResult")
    override fun initAction() {
        ApiModel.fetVideosInfo2()
            .subscribe({
                adapter = Pager2Adapter(it)
                vp2.adapter = adapter
                it.forEach { video ->
                    Log.d("zhang", video.url)
                }
            }, {
                Log.d("zhang", it.localizedMessage?: "error")
            })
    }

    companion object {
        fun launch(ctx: Context) {
            val intent = Intent(ctx, VideoActivity::class.java)
            ctx.startActivity(intent)
        }
    }

}
