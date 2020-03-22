package com.example.androidhub.exoplayer

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.androidhub.utils.AppUtil
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.Cache
import com.google.android.exoplayer2.upstream.cache.CacheDataSourceFactory
import com.google.android.exoplayer2.util.Util
import java.io.File
import java.lang.ref.WeakReference

class ExoPlayerHelper private constructor(val player: ExoPlayer) :
    LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun play() {
        player.playWhenReady = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun pause() {
        player.playWhenReady = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun release() {
        player.release()
        VideoCache.release()
    }

    fun test() {
        
    }

    fun setDefaultListener() {
        val eventListener: Player.EventListener = object : Player.EventListener {
            override fun onTimelineChanged(timeline: Timeline, reason: Int) {
                //
                Log.d(TAG, "onTimelineChanged")
            }

            override fun onTracksChanged(
                trackGroups: TrackGroupArray,
                trackSelections: TrackSelectionArray
            ) {
                //
                Log.d(TAG, "onTracksChanged")
            }

            override fun onLoadingChanged(isLoading: Boolean) {
                //
                Log.d(TAG, "onLoadingChanged")
            }

            override fun onPlayerStateChanged(
                playWhenReady: Boolean,
                playbackState: Int
            ) {
                Log.d(TAG, "onLoadingChanged")
                //
                when (playbackState) {
                    Player.STATE_IDLE -> {
                        Log.d(TAG, "STATE_IDLE")
                    }
                    Player.STATE_BUFFERING -> {
                        Log.d(TAG, "STATE_BUFFERING")
                    }
                    Player.STATE_READY -> {
                        Log.d(TAG, "STATE_READY")
                    }
                    Player.STATE_ENDED -> {
                        Log.d(TAG, "STATE_ENDED")
                    }
                }
            }

            override fun onPlaybackSuppressionReasonChanged(playbackSuppressionReason: Int) {
                Log.d(TAG, "onPlaybackSuppressionReasonChanged")
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                Log.d(TAG, "onPlayerError")
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                Log.d(TAG, "onRepeatModeChanged")
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                Log.d(TAG, "onShuffleModeEnabledChanged")
            }

            override fun onPlayerError(error: ExoPlaybackException) {
                Log.d(TAG, "onPlayerError")
            }

            override fun onPositionDiscontinuity(reason: Int) {
                Log.d(TAG, "onPositionDiscontinuity")
            }

            override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters) {
                Log.d(TAG, "onPlaybackParametersChanged")
            }

            override fun onSeekProcessed() {
                Log.d(TAG, "onSeekProcessed")
            }
        }
        player.addListener(eventListener)

    }

    class Builder(context: Context) {

        var player: ExoPlayer? = null

        private var dataSourceFactory: DataSource.Factory? = null

        private var simpleCache: Cache? = null

        private val uriList = arrayListOf<Uri>()

        private var userAgent: String

        private val weakContext: WeakReference<Context> = WeakReference(context)

        init {
            val applicationName = AppUtil.getApplicationName(context)
            userAgent = Util.getUserAgent(context, applicationName)
            simpleCache = VideoCache.getInstance(context)
            initializePlayer(context)
        }

        fun addUrl(url: String): Builder {
            if (url.contains(Regex("https?://"))) {
                uriList.add(Uri.parse(url))
            } else {
                uriList.add(Uri.fromFile(File(url)))
            }
            return this
        }

        fun addUrls(urls: List<String>): Builder {
            urls.forEach { s ->
                addUrl(s)
            }
            return this
        }

        fun setPlayView(playerView: PlayerView): Builder {
            playerView.player = player
            return this
        }

        fun useCache(maxBytes: Long): Builder {
            weakContext.get()?.let {
                simpleCache = VideoCache.getInstance(it, maxBytes)
                dataSourceFactory = CacheDataSourceFactory(simpleCache, dataSourceFactory)
            }
            return this
        }

        // 此处有强转
        fun build(): ExoPlayerHelper {
            prepare()
            return ExoPlayerHelper(player!!)
        }

        // 初始化 ExoPlayer
        private fun initializePlayer(context: Context) {
            player = SimpleExoPlayer.Builder(context).build()
            dataSourceFactory = DefaultDataSourceFactory(context, userAgent)
        }

        // 准备视频
        private fun prepare() {
            val mediaSource: MediaSource
            when (uriList.size) {
                0 -> return
                1 -> mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(uriList[0])
                else -> {
                    mediaSource = ConcatenatingMediaSource(true)
                    uriList.forEach {
                        val mMediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(it)
                        mediaSource.addMediaSource(mMediaSource)
                    }
                }
            }
            player?.prepare(mediaSource)
        }
    }

    companion object {

        private const val TAG = "ExoPlayerHelper"

    }

}