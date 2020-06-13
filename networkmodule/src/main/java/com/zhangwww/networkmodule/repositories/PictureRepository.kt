package com.zhangwww.networkmodule.repositories

import com.zhangwww.networkmodule.api.GankApi
import com.zhangwww.networkmodule.api.RetrofitManager
import com.zhangwww.networkmodule.model.PictureModel

class PictureRepository {

    private val api by lazy { RetrofitManager.create(GankApi::class.java) }

    suspend fun fetchGirlPictures(page: Int, count: Int): List<PictureModel> {
        val newPage = if (page <= 0) 1 else page
        val newCount = if (count !in 10..50) 10 else count
        return api.fetchPictureList(newPage, newCount).data
    }

}