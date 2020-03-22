package com.example.androidhub.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.androidhub.R
import com.example.androidhub.viewmodel.DaggerViewModel

class DaggerActivity : AppCompatActivity() {

    private lateinit var viewModel: DaggerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)
        viewModel = ViewModelProvider(this).get(DaggerViewModel::class.java)
    }
}
