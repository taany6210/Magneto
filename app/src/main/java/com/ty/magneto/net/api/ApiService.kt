package com.ty.magneto.net.api

import com.ty.magneto.bean.Find
import com.ty.magneto.bean.Reply
import com.ty.magneto.bean.User
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 20:22
 * @ 描述:
 */
interface ApiService {

    /**
     * 用户登录
     */
    @POST("/api/user/login")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Observable<Reply<User>>


    /**
     * 获取排行榜
     */
    @GET("api/v1/novel/rank-category")
    fun rankCategory(): Observable<Reply<Find>>
}