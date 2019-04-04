package com.ty.loadingpager

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.ty.loadingpager.databinding.PageEmptyBinding
import com.ty.loadingpager.databinding.PageErrorBinding
import com.ty.loadingpager.databinding.PageLoadingBinding


/**
 * @ 创建者:   ty
 * @ 时间:    2018/11/5 10:57
 * @ 描述:
 */
abstract class LoadingPager @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : FrameLayout(context, attrs), DataLoadListener, View.OnClickListener {

    companion object {
        /**
         * 无状态
         */
        private const val STATE_NONE = -1
        /**
         * 加载中的状态
         */
        const val STATE_LOADING = 0
        /**
         * 空的状态
         */
        const val STATE_EMPTY = 1
        /**
         * 错误的状态
         */
        const val STATE_ERROR = 2
        /**
         * 成功的状态
         */
        const val STATE_SUCCESS = 3
    }

    /**
     * 当前view的状态
     */
    private var currentState = STATE_NONE
    /**
     * 正在加载中的view
     */
    private var loadingBinding: PageLoadingBinding? = null
    /**
     * 没有数据的view
     */
    private var emptyBinding: PageEmptyBinding? = null
    /**
     * 加载失败的view
     */
    private var errorBinding: PageErrorBinding? = null

    /**
     * 成功的view
     */
    private var mSuccessView: View? = null

    private val inflate: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    init {
        initView()
    }


    /**
     * 初始化
     */
    private fun initView() {
        /**
         * 1. 加载数据
         */
        if (loadingBinding == null) {
            loadingBinding = DataBindingUtil.inflate(inflate, R.layout.page_loading, null, false)
            //loadingBinding?.avLoading?.show()
            addView(loadingBinding!!.root)
        }

        /**
         * 2. 数据为空
         */
        if (emptyBinding == null) {
            emptyBinding = DataBindingUtil.inflate(inflate, R.layout.page_empty, null, false)
            addView(emptyBinding!!.root)
            emptyBinding!!.root.setOnClickListener(this)
        }

        /**
         * 3. 加载失败
         */
        if (errorBinding == null) {
            errorBinding = DataBindingUtil.inflate(inflate, R.layout.page_error, null, false)
            addView(errorBinding!!.root)
            errorBinding!!.root.setOnClickListener(this)
        }
        /**
         * 4. 成功
         */
        if (mSuccessView == null) {
            mSuccessView = onCreateSuccessView()
            val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            addView(mSuccessView, params)
        }

        safeUpdateUIStyle()
    }

    /**
     * 根据状态显示view
     */
    private fun safeUpdateUIStyle() {
        Runnable { updateUIStyle() }
    }

    private fun updateUIStyle() {

        /**
         * 1.loading
         */
        if (loadingBinding != null) {
            if (currentState == STATE_LOADING || currentState == STATE_NONE) {
                loadingBinding!!.root.visibility = View.VISIBLE
            } else {
                loadingBinding!!.root.visibility = View.GONE
            }
            if (currentState == STATE_LOADING) {
                bringChildToFront(loadingBinding!!.root)
                return
            }
        }

        /**
         * 2. empty
         */
        if (emptyBinding != null) {
            if (currentState == STATE_EMPTY) {
                emptyBinding!!.root.visibility = View.VISIBLE
            } else {
                emptyBinding!!.root.visibility = View.GONE
            }
            if (currentState == STATE_EMPTY) {
                bringChildToFront(emptyBinding!!.root)
            }
        }


        /**
         * 3. error
         */
        if (errorBinding != null) {
            if (currentState == STATE_ERROR) {
                errorBinding!!.root.visibility = View.VISIBLE
            } else {
                errorBinding!!.root.visibility = View.GONE
            }
            if (currentState == STATE_ERROR) {
                bringChildToFront(errorBinding!!.root)
            }
        }

        /**
         * 4. success
         */
        if (mSuccessView != null) {
            if (currentState == STATE_SUCCESS) {
                mSuccessView!!.visibility = View.VISIBLE
            } else {
                mSuccessView!!.visibility = View.GONE
            }
        }
    }

    /**
     * 加载数据的方法
     */
    fun loadData() {
        if (currentState == STATE_EMPTY || currentState == STATE_ERROR || currentState == STATE_NONE) {
            currentState = STATE_LOADING
            onStartLoadData()
        }
        safeUpdateUIStyle()
    }

    override fun onDataLoading(result: LoadedResult) {
        currentState = result.state
        safeUpdateUIStyle()
    }

    protected abstract fun onCreateSuccessView(): View

    protected abstract fun onStartLoadData()

    open fun reloadData() {}

    enum class LoadedResult constructor(state: Int) {
        LOADING(STATE_LOADING), EMPTY(STATE_EMPTY), ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS);

        var state: Int = 0
            internal set

        init {
            this.state = state
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.ll_reload) {
            currentState = STATE_LOADING
            safeUpdateUIStyle()
            reloadData()
        }
    }
}
