package com.example.androidhub.api

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private val builder = Builder()

    private var retrofit: Retrofit? = null

    private fun getInstance(): Retrofit {
        if (retrofit == null) {
            retrofit = builder.build()
        }
        return retrofit!!
    }

    fun <T> create(clazz: Class<T>): T {
        return getInstance().create(clazz)
    }

    fun createNewRetrofit(url: String): Retrofit {
        return builder.defaultBuild(url);
    }

    class Builder {

        private val READ_TIME_OUT = 10 * 1000L
        private val WRITE_TIME_OUT = 10 * 1000L
        private val CONNECT_TIME_OUT = 10 * 1000L

        private val cookieStore = HashMap<String, List<Cookie>>()
        val httpClient = OkHttpClient.Builder()
        val retrofitBuilder = Retrofit.Builder()

        // OKHttpClient 部分

        fun setReadTimeOut(
            duration: Long = READ_TIME_OUT,
            unit: TimeUnit = TimeUnit.MILLISECONDS
        ): Builder {
            httpClient.readTimeout(duration, unit)
            return this
        }

        fun setWriteTimeOut(
            duration: Long = WRITE_TIME_OUT,
            unit: TimeUnit = TimeUnit.MILLISECONDS
        ): Builder {
            httpClient.writeTimeout(duration, unit)
            return this
        }

        fun setConnectTimeout(
            duration: Long = CONNECT_TIME_OUT,
            unit: TimeUnit = TimeUnit.MILLISECONDS
        ): Builder {
            httpClient.connectTimeout(duration, unit)
            return this
        }

        fun setHttpLogLevel(level: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY): Builder {
            httpClient.addInterceptor(HttpLoggingInterceptor().apply {
                this.level = level
            })
            return this
        }

        fun setHttpLoggingInterceptor(interceptor: HttpLoggingInterceptor): Builder {
            httpClient.addInterceptor(interceptor)
            return this
        }

        fun useCookie(use: Boolean = false): Builder {
            if (use) {
                httpClient.cookieJar(object : CookieJar {
                    override fun loadForRequest(url: HttpUrl): List<Cookie> {
                        return cookieStore[url.host] ?: arrayListOf()
                    }

                    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                        cookieStore[url.host] = cookies
                    }

                })
            }
            return this
        }


        // Retrofit 部分

        fun setBaseUrl(url: String): Builder {
            retrofitBuilder.baseUrl(url)
            return this
        }

        fun addConverterFactory(factory: Converter.Factory = GsonConverterFactory.create()): Builder {
            retrofitBuilder.addConverterFactory(factory)
            return this
        }

        fun addCallAdapter(factory: CallAdapter.Factory = RxJava2CallAdapterFactory.create()): Builder {
            retrofitBuilder.addCallAdapterFactory(factory)
            return this
        }

        fun build(): Retrofit {
            return retrofitBuilder
                .client(httpClient.build())
                .build()
        }

        fun defaultBuild(url: String): Retrofit {
            setReadTimeOut()
            setWriteTimeOut()
            setConnectTimeout()
            setBaseUrl(url)
            setHttpLogLevel()
            addCallAdapter()
            addConverterFactory()
            return build()
        }
    }

}