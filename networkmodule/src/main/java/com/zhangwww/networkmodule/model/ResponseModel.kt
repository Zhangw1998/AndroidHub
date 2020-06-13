package com.zhangwww.networkmodule.model

data class ResponseModel<T>(
    var data: T,
    var msg: String = "success",
    var code: Int = 200
) {

}