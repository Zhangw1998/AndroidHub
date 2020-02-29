package com.example.androidhub.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.HttpDataSource.HttpDataSourceException
import com.google.android.exoplayer2.upstream.HttpDataSource.InvalidResponseCodeException
import com.google.android.exoplayer2.util.Util

object ExoplayerHelper {

    private const val TAG = "ExoplayerHelper"

    var player: ExoPlayer? = null

    fun initializePlayer(
        playerView: PlayerView,
        url: String
    ) {
        val context = playerView.context
        val uri = Uri.parse(url)
        player = SimpleExoPlayer.Builder(context).build()
        val dataSourceFactory: DataSource.Factory =
            DefaultDataSourceFactory(
                context,
                Util.getUserAgent(context, "ApplicationName")
            )
        val mediaSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri)
        player?.prepare(mediaSource)
        playerView.player = player
    }

    fun release() {
        player?.release()
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
        player?.addListener(eventListener)

    }

}