package com.example.androidhub.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidhub.R
import com.example.androidhub.bean.ImageData
import com.example.androidhub.bean.TestVideo
import com.example.androidhub.exoplayer.ExoPlayerHelper
import kotlinx.android.synthetic.main.item_video.*


class Pager2Fragment: Fragment() {

    private val TAG = this.javaClass.simpleName

    private var helper: ExoPlayerHelper? = null

    private var testVideo: TestVideo? = null

    private var videoData: ImageData? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_video, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        when(val argument = arguments?.getSerializable(DATA)) {
            is TestVideo -> {
                testVideo = argument
            }
            is ImageData-> {
                videoData = argument
            }
        }
        testVideo?.let {
            helper = ExoPlayerHelper.Builder(requireContext())
                .addUrl(it.url)
                .build()
            exo_pv_item.player = helper?.player
            tv_title.text = it.videoTitle
        }
        videoData?.let {
            helper = ExoPlayerHelper.Builder(requireContext())
                .addUrl(it.url)
                .build()
            exo_pv_item.player = helper?.player
            tv_title.text = it.name
        }
    }

    override fun onResume() {
        super.onResume()
        helper?.play()
        Log.d(TAG, "onResume: ${this.id}")
    }

    override fun onPause() {
        super.onPause()
        helper?.pause()
        Log.d(TAG, "onPause: ${this.id}")
    }

    companion object {

        private const val DATA = "data"

        fun newInstance(testVideo: TestVideo? = null, imageData: ImageData? = null): Pager2Fragment {
            val fragment = Pager2Fragment()
            fragment.arguments = Bundle().apply {
                testVideo?.let {
                    putSerializable(DATA, it)
                }
                imageData?.let {
                    putSerializable(DATA, it)
                }
            }
            return fragment
        }

    }

}