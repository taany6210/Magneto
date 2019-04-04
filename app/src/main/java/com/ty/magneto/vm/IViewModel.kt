package com.ty.magneto.vm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import org.jetbrains.annotations.NotNull

/**
 * @ 创建者:   ty
 * @ 时间:    2019/4/3 15:06
 * @ 描述:
 */
interface IViewModel : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun oncreate(@NotNull owner: LifecycleOwner)
}