package com.ty.loadingpager

/**
 * @ 创建者:   ty
 * @ 时间:    2018/12/6 10:15
 * @ 描述:
 */
interface DataLoadListener {
    fun onDataLoading(result: LoadingPager.LoadedResult)
}