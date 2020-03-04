package com.example.androidhub.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidhub.R
import com.example.androidhub.bean.TestVideo
import kotlinx.android.synthetic.main.item_video.view.*

class Pager2Adapter(private val videoList: List<TestVideo>): RecyclerView.Adapter<Pager2Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val video = videoList[position]
        holder.bind(video)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(video: TestVideo) {
            with(itemView) {
                tv_title.text = video.videoTitle
            }
        }

        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val ctx = parent.context
                val view = LayoutInflater.from(ctx).inflate(R.layout.item_video, parent, false);
                return ViewHolder(view)
            }
        }
    }

}