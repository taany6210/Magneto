package com.ty.magneto.bean

import java.io.Serializable

/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 15:36
 * @ 描述:    model
 */
data class Reply<T>(
    var code: Int,
    var msg: String,
    var err: String,
    var data: T
)

open class BaseBean : Serializable

data class User(
    var username: String,
    var password: String
) : BaseBean()


data class Category(
    var _id: String,
    var title: String,
    var cover: String,
    var collapse: Boolean,
    var shortTitle: String,
    var check: Boolean,
    var index: Int
) : BaseBean()

data class Find(
    var epub: List<Category>,
    var female: List<Category>,
    var picture: List<Category>,
    var male: List<Category>,
    var ok: Boolean
) : BaseBean()
