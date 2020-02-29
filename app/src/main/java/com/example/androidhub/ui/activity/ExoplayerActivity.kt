package com.example.androidhub.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.example.androidhub.R
import com.example.androidhub.api.ApiModel
import com.example.androidhub.api.RetrofitManager
import com.example.androidhub.extension.toast
import com.example.androidhub.utils.ExoplayerHelper
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_exoplayer.*

class ExoplayerActivity : AppCompatActivity() {

    private var player: SimpleExoPlayer? = null

    private var titleList: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exoplayer)
        val url = "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4"
//        initializePlayer(this, Uri.parse(url))
//        exo_pv.player = player
        fetchVideos()
        btn_choose.setOnClickListener {
            showDialog()
        }
    }

    override fun onStart() {
        super.onStart()
        player?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player?.playWhenReady = false
    }

    @SuppressLint("CheckResult")
    private fun fetchVideos() {
        ApiModel.fetVideosInfo2()
            .subscribe({
                player = SimpleExoPlayer.Builder(this).build()
                val userAgent = Util.getUserAgent(this, "ApplicationName")
                val dataSourceFactory = DefaultDataSourceFactory(this, userAgent)
                val concatenatingMediaSource = ConcatenatingMediaSource(true)

                val titles = mutableListOf<String>()
                it.forEach { video ->
                    val uri = Uri.parse(video.url)
                    val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
                    concatenatingMediaSource.addMediaSource(mediaSource)
                    titles.add(video.videoTitle)
                }
                titleList = titles

                player?.prepare(concatenatingMediaSource)
                exo_pv.player = player
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

    private fun initializePlayer(context: Context, mp4VideoUri: Uri) {
        player = SimpleExoPlayer.Builder(context).build()
        val userAgent = Util.getUserAgent(context, "ApplicationName")
        // DataSource.Factory
        val dataSourceFactory = DefaultDataSourceFactory(context, userAgent)
        // MediaSource
        val videoSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mp4VideoUri)
        player?.prepare(videoSource)
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

    companion object {

        fun launch(ctx: Context) {
            val intent = Intent(ctx, ExoplayerActivity::class.java)
            ctx.startActivity(intent)
        }

    }
}
