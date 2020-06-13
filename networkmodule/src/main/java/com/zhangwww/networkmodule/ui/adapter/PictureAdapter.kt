package com.zhangwww.networkmodule.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zhangwww.networkmodule.R
import com.zhangwww.networkmodule.model.PictureModel
import kotlinx.android.synthetic.main.item_picture.view.*

class PictureAdapter(private val pictures: ArrayList<PictureModel>): RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = pictures.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(pictures[position])
    }

    fun updateAdapterList(list: List<PictureModel>) {
        pictures.clear()
        list.forEach { pictures.add(it) }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(model: PictureModel) {
            with(itemView) {
                Glide.with(this)
                    .load(model.url)
                    .into(iv_picture)
            }
        }
    }
}