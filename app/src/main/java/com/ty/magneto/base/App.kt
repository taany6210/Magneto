package com.ty.magneto.base

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 23:23
 * @ 描述:
 */
open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }

}