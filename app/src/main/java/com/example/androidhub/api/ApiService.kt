package com.example.androidhub.api

import com.example.androidhub.bean.Trailers
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET()
    fun fetTestVideo(@Url url: String): Maybe<Trailers>

    @GET("http://api.m.mtime.cn/PageSubArea/TrailerList.api")
    fun fetTestVideo2(): Maybe<Trailers>

}