package com.example.androidhub.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidhub.R
import com.example.androidhub.api.ApiModel
import com.example.androidhub.extension.toast
import com.example.androidhub.ui.adapter.ImageAdapter
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ApiModel.fetchImges()
            .subscribe({
                val adapter = ImageAdapter(it)
                rv_img.adapter = adapter
            }, {
                toast("Filed to fetch images")
            })
    }

    companion object {

        fun launch(ctx: Context) {
            val intent = Intent(ctx, RegisterActivity::class.java)
            ctx.startActivity(intent)
        }

    }

}
