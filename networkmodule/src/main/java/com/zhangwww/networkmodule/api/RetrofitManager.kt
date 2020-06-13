package com.zhangwww.networkmodule.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    // 时间单位
    private val TIME_UNIT = TimeUnit.SECONDS

    // 连接超时时长
    private const val CONNECT_TIME_OUT = 10L

    // 读取超时时长
    private const val READ_TIME_OUT = 10L

    // 写入超时时长
    private const val WRITE_TIME_OUT = 10L

    // Gank api
    private const val baseUrl: String = "https://gank.io/api/v2/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIME_OUT, TIME_UNIT)
            .readTimeout(READ_TIME_OUT, TIME_UNIT)
            .writeTimeout(WRITE_TIME_OUT, TIME_UNIT)
            .build())
        .build()

    fun <T>create(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

}