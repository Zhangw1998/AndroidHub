package com.example.androidhub.api

import com.example.androidhub.bean.TestVideo
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Url

object ApiModel {

    private val service = RetrofitManager.Builder()
        .defaultBuild("http://api.m.mtime.cn/")
        .create(ApiService::class.java)

    fun fetVideosInfo(): Maybe<List<TestVideo>> {
        return service.fetTestVideo("http://api.m.mtime.cn/PageSubArea/TrailerList.api")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                it.trailers
            }
    }

    fun fetVideosInfo2(): Maybe<List<TestVideo>> {
        return service.fetTestVideo2()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .map {
                it.trailers
            }
    }

}