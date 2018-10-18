package com.ty.magneto.bean

import java.io.Serializable

/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 15:36
 * @ 描述:
 */
open class Reply : Serializable

data class User(var username: String, var password: String) : Reply()
