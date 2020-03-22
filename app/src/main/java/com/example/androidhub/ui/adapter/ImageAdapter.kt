package com.example.androidhub.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidhub.R
import com.example.androidhub.bean.ImageData
import kotlinx.android.synthetic.main.item_image.view.*


class ImageAdapter(val imgList: List<ImageData>): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(data: ImageData) {
            with(itemView) {
                tv_name.text = data.name
                Glide.with(this)
                    .load(data.url)
                    .into(iv_img)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imgList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(imgList[position])
    }
}