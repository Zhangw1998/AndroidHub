package com.example.androidhub.api

import com.example.androidhub.bean.ImageData
import com.example.androidhub.bean.Login
import com.example.androidhub.bean.Trailers
import io.reactivex.Maybe
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

interface ApiService {

    @GET()
    fun fetTestVideo(@Url url: String): Maybe<Trailers>

    @GET("http://api.m.mtime.cn/PageSubArea/TrailerList.api")
    fun fetTestVideo(): Maybe<Trailers>

    @POST("/login/cellphone")
    fun login(
        @Field("phone") phone: String,
        @Field("password") password: String
    ): Maybe<Login>

    @POST("/login")
    fun loginByUseEmail(
        @Field("email") email: String,
        @Field("password") password: String
    ): Maybe<Login>

    @POST("/login/refresh")
    fun refreshLogin(): Maybe<String>

    @POST("/captcha/sent")
    fun sendCode(
        @Field("phone") phone: String,
        @Field("ctcode") code: String = "86"
    ): Maybe<String>

    @POST
    fun verifyCode(
        @Field("phone") phone: String,
        @Field("captcha") captcha: String,
        @Field("ctcode") code: String = "86"
    ): Maybe<String>

    @GET("http://47.115.32.41:8000/imgrec/data")
    fun fetchImages(): Maybe<List<ImageData>>

    @GET("http://47.115.32.41:8000/video/data")
    fun fetchVideos(): Maybe<List<ImageData>>

    @GET("http://47.115.32.41:8000/update")
    fun update(): Maybe<Any>

}