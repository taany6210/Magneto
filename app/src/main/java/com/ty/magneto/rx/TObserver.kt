package com.ty.magneto.rx

import com.ty.magneto.bean.Reply
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 22:51
 * @ 描述:
 */
abstract class TObserver<T> : Observer<Reply<T>> {

    override fun onSubscribe(d: Disposable) {

    }

    override fun onNext(reply: Reply<T>) {
        if (reply.code == 1) {
            if (reply.data != null) {
                onSuccess(reply.data)
            } else {
                onError("data is null")
            }
        } else {
            onError(reply.err)
        }

    }

    override fun onError(e: Throwable) {
        println(e.message)
    }

    override fun onComplete() {
        println("请求完成")
    }

    abstract fun onSuccess(it: T)

    private fun onError(message: String) {
        println(message)
    }
}