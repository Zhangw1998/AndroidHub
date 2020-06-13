package com.zhangwww.networkmodule.api

import com.zhangwww.networkmodule.model.PictureModel
import com.zhangwww.networkmodule.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * https://gank.io/api
 */
interface GankApi {

    /**
     * 获取图片列表
     * @param page 页数: >= 1
     * @param count 一页加载的数量: [10, 50]
     */
    @GET("data/category/Girl/type/Girl/page/{page}/count/{count}")
    suspend fun fetchPictureList(@Path("page") page: Int, @Path("count") count: Int): ResponseModel<List<PictureModel>>

}