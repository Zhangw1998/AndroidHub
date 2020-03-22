package com.example.androidhub.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.androidhub.R
import com.example.androidhub.api.ApiModel
import com.example.androidhub.extension.toast
import com.example.androidhub.exoplayer.ExoPlayerHelper
import com.google.android.exoplayer2.ExoPlayer
import kotlinx.android.synthetic.main.activity_exoplayer.*

class ExoplayerActivity : AppCompatActivity() {

    private var player: ExoPlayer? = null

    private var titleList: List<String>? = null

    private var helper: ExoPlayerHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exoplayer)
        btn_choose.setOnClickListener {
            showDialog()
        }
        fetchVideos2()
    }

    @SuppressLint("CheckResult")
    private fun fetchVideos2() {
        ApiModel.fetVideosInfo()
            .subscribe({
                toast("fetch success")
                titleList = it.map { video -> video.videoTitle }
                helper = ExoPlayerHelper.Builder(this)
                    .setPlayView(exo_pv)
//                    .useCache(512*1024*1024)
                    .addUrls(it.map { video -> video.url })
                    .build().apply {
                        this@ExoplayerActivity.player = this.player
                        lifecycle.addObserver(this)
                    }
            }, {
                toast("fetch failed")
            })
    }


    private fun showDialog() {
        titleList?.let {
            AlertDialog.Builder(this)
                .setItems(it.toTypedArray()) { dialog, which ->
                    dialog.cancel()
                    player?.seekToDefaultPosition(which)
                }.show()
        }
    }

    companion object {

        fun launch(ctx: Context) {
            val intent = Intent(ctx, ExoplayerActivity::class.java)
            ctx.startActivity(intent)
        }

    }
}
