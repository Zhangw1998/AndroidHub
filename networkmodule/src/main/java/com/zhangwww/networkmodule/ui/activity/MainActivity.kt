package com.zhangwww.networkmodule.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.zhangwww.networkmodule.R
import com.zhangwww.networkmodule.ui.adapter.PictureAdapter
import com.zhangwww.networkmodule.viewmodels.PictureViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(PictureViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = PictureAdapter(arrayListOf())
        rv_pictures.adapter = adapter
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_pictures)
        viewModel.pictureList.observe(this, Observer {
            adapter.updateAdapterList(it)
        })
        viewModel.updatePictureList(1, 30)
    }
}
