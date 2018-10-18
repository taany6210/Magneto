package com.ty.magneto.net.retrofit

import com.ty.magneto.BuildConfig
import com.ty.magneto.net.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 21:26
 * @ 描述:
 */
object RetrofitApi {

    private val host = if (BuildConfig.DEBUG) BuildConfig.host else BuildConfig.host

    private val client = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        //.addInterceptor(loggingInterceptor)
        .build()

    val apiService: ApiService = Retrofit.Builder()
        .baseUrl(host)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(client)
        .build()
        .create(ApiService::class.java)
}