package com.example.androidhub.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


//{
//    "id": 75289,
//    "movieName": "《速度与激情:特别行动》曝全新中文预告",
//    "coverImg": "http://img5.mtime.cn/mg/2019/06/29/002009.16684021_120X90X4.jpg",
//    "movieId": 254114,
//    "url": "http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4",
//    "hightUrl": "http://vfx.mtime.cn/Video/2019/06/29/mp4/190629004821240734.mp4",
//    "videoTitle": "速度与激情：特别行动 中文预告",
//    "videoLength": 146,
//    "rating": -1,
//    "type": [
//    "动作",
//    "冒险"
//    ],
//    "summary": ""
//}

data class TestVideo(
    var id: Long = 0,
    var movieName: String = "",
    var coverImg: String = "",
    var movieId: Long = 0,
    var url: String = "",
    var hightUrl: String = "",
    var videoTitle: String = "",
    var videoLength: Int = 0,
    var rating: Float = 0f,
    var type: List<String>,
    var summary: String = ""
) : Serializable

data class Trailers(
    var trailers: List<TestVideo>
) : Serializable