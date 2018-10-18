package com.ty.magneto.rx

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 20:45
 * @ 描述:    线程调度
 */
class RxSchedulers<T> {
    fun <T> compose(): ObservableTransformer<T, T> {
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    //订阅开始前调用，可以在此做一些网络获取数据环境的判断
                    it.isDisposed
                }
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}