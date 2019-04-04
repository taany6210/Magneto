package com.ty.magneto.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider

/**
 * @ 创建者:   ty
 * @ 时间:    2019/4/3 14:44
 * @ 描述:
 */
abstract class AutoDisposeActivity : AppCompatActivity() {
    protected val scopeProvider: AndroidLifecycleScopeProvider by lazy {
        AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY)
    }
}