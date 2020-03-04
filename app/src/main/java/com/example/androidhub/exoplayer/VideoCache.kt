package com.example.androidhub.exoplayer

import android.content.Context
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File


object VideoCache {

    private const val cachePath = "cache_exoplayer"

    private var sDownloadCache: SimpleCache? = null

    fun getInstance(context: Context, maxBytes: Long = 512 * 1024 * 1024): SimpleCache {
        if (sDownloadCache == null) {
            sDownloadCache = SimpleCache(
                getCacheFile(context),
                LeastRecentlyUsedCacheEvictor(maxBytes),
                ExoDatabaseProvider(context)
            )
        }
        return sDownloadCache!!
    }


    fun release() {
        sDownloadCache?.release()
        sDownloadCache = null
    }

    fun clear(context: Context) {
        SimpleCache.delete(getCacheFile(context), ExoDatabaseProvider(context))
    }

    private fun getCacheFile(context: Context): File {
        return File(context.externalCacheDir, cachePath)
    }

}