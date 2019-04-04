package com.ty.magneto.base

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.ty.loadingpager.LoadingPager
import com.ty.magneto.R
import com.ty.magneto.databinding.ActivityBaseBinding


abstract class BaseActivity<B : ViewDataBinding> : AutoDisposeActivity() {

    protected var mPager: LoadingPager? = null
    protected lateinit var mBinding: B
    private lateinit var mBaseBinding: ActivityBaseBinding
    private var needInit = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        val myTid = android.os.Process.myTid()
        mBaseBinding = DataBindingUtil.inflate(layoutInflater, R.layout.activity_base, null, false)
        val rootLayout = mBaseBinding.root as LinearLayout
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT
        )

        if (mPager == null) {
            mPager = object : LoadingPager(this@BaseActivity, null) {
                override fun reloadData() {
                    super.reloadData()
                    this@BaseActivity.reloadData()
                }

                override fun onStartLoadData() {
                    initData(savedInstanceState)
                }

                override fun onCreateSuccessView(): View {
                    mBinding = DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)
                    initView(savedInstanceState)
                    initListener()
                    return mBinding.root
                }

                override fun onDataLoading(result: LoadedResult) {
                    super.onDataLoading(result)
                    if (result == LoadedResult.SUCCESS) {
                        if (needInit) {
                            needInit = false
                        }
                    }
                }
            }
        } else {
            val parent = mPager!!.parent
            if (parent != null && parent is ViewGroup) {
                parent.removeView(mPager)
            }
        }
        mPager!!.loadData()
        rootLayout.addView(mPager, params)
        setContentView(rootLayout)

    }

    /**
     * 获取子类layout布局ID 必须实现
     *
     * @return
     */
    abstract fun getLayoutId(): Int

    /**
     * 初始化视图 子类必须实现
     *
     * @return
     */
    protected abstract fun initView(savedInstanceState: Bundle?)

    /**
     * 加载数据 子类必须实现
     */
    protected abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 初始化事件 子类选择实现
     */
    protected fun initListener() {

    }

    /**
     * 重新加载数据  错误或者空页面点击
     */
    protected fun reloadData() {}

    override fun onDestroy() {
        super.onDestroy()
        mBinding.unbind()
    }
}
