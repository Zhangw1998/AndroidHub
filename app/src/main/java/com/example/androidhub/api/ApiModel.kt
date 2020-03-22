package com.example.androidhub.api

import com.example.androidhub.bean.ImageData
import com.example.androidhub.bean.Login
import com.example.androidhub.bean.TestVideo
import com.example.androidhub.utils.RxUtil
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object ApiModel {

    private val service = RetrofitManager.Builder()
        .defaultBuild("http://182.92.225.180:3000/")
        .create(ApiService::class.java)

    fun fetVideosInfo(): Maybe<List<TestVideo>> {
        // http://api.m.mtime.cn/PageSubArea/TrailerList.api
        return service.fetTestVideo()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                it.trailers
            }
    }

    fun login(phone: String, password: String): Maybe<Login> {
        return service.login(phone, password)
            .compose(RxUtil.maybeToMain())
    }

    fun fetchImges(): Maybe<List<ImageData>> {
        return service.fetchImages()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun fetchVideos(): Maybe<List<ImageData>> {
        return service.fetchVideos()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}