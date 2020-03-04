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
        val url = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"
//        initializePlayer(this, Uri.parse(url))
//        exo_pv.player = player
//        fetchVideos()

//        helper = ExoPlayerHelper.Builder(this)
//            .addUrl(url)
//            .setPlayView(exo_pv)
//            .useCache(512*1024*1024)
//            .build()
//        helper?.let {
//            lifecycle.addObserver(it)
//        }
        btn_choose.setOnClickListener {
            showDialog()
        }
        fetchVideos2()
    }

//    override fun onStart() {
//        super.onStart()
//        player?.playWhenReady = true
//    }
//
//    override fun onStop() {
//        super.onStop()
//        player?.playWhenReady = false
//    }


    @SuppressLint("CheckResult")
    private fun fetchVideos2() {
        ApiModel.fetVideosInfo2()
            .subscribe({
                toast("fetch success")
                titleList = it.map { video -> video.videoTitle }
                helper = ExoPlayerHelper.Builder(this)
                    .setPlayView(exo_pv)
                    .useCache(512*1024*1024)
                    .addUrls(it.map { video -> video.url })
                    .build().apply {
                        this@ExoplayerActivity.player = this.player
                        lifecycle.addObserver(this)
                    }
            }, {
                toast("fetch failed")
            })
    }

//    @SuppressLint("CheckResult")
//    private fun fetchVideos() {
//        ApiModel.fetVideosInfo2()
//            .subscribe({
//                player = SimpleExoPlayer.Builder(this).build()
//                val userAgent = Util.getUserAgent(this, "ApplicationName")
//                val dataSourceFactory = DefaultDataSourceFactory(this, userAgent)
//                val concatenatingMediaSource = ConcatenatingMediaSource(true)
//
//                val titles = mutableListOf<String>()
//                it.forEach { video ->
//                    val uri = Uri.parse(video.url)
//                    val mediaSource =
//                        ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
//                    concatenatingMediaSource.addMediaSource(mediaSource)
//                    titles.add(video.videoTitle)
//                }
//                titleList = titles
//
//                player?.prepare(concatenatingMediaSource)
//                exo_pv.player = player
//            }, {
//                toast("fetch failed")
//            })
//    }

    private fun showDialog() {
        titleList?.let {
            AlertDialog.Builder(this)
                .setItems(it.toTypedArray()) { dialog, which ->
                    dialog.cancel()
                    player?.seekToDefaultPosition(which)
                }.show()
        }
    }

//    private fun initializePlayer(context: Context, mp4VideoUri: Uri) {
//        player = SimpleExoPlayer.Builder(context).build()
//        val userAgent = Util.getUserAgent(context, "ApplicationName")
//        // DataSource.Factory
//        val dataSourceFactory = DefaultDataSourceFactory(context, userAgent)
//        // MediaSource
//        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mp4VideoUri)
//        player?.prepare(videoSource)
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        player?.release()
//    }

    companion object {

        fun launch(ctx: Context) {
            val intent = Intent(ctx, ExoplayerActivity::class.java)
            ctx.startActivity(intent)
        }

    }
}
