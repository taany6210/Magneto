package com.ty.magneto.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.databinding.DataBindingUtil
import com.blankj.utilcode.util.LogUtils
import com.ty.loadingpager.LoadingPager
import com.ty.magneto.R
import com.ty.magneto.base.BaseActivity
import com.ty.magneto.bean.Find
import com.ty.magneto.bean.User
import com.ty.magneto.databinding.ActivityMainBinding
import com.ty.magneto.net.retrofit.RetrofitApi
import com.ty.magneto.rx.RxSchedulers
import com.ty.magneto.rx.TObserver


/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 15:51
 * @ 描述:    main
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    @SuppressLint("AutoDispose")
    override fun initView(savedInstanceState: Bundle?) {
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.user = User("张三", "123456")

        println(binding.user)


        binding.bnvMain.setOnNavigationItemSelectedListener {
            Toast.makeText(this, it.title, Toast.LENGTH_SHORT).show()
            true
        }

        binding.bnvMain.setOnNavigationItemReselectedListener {
            Toast.makeText(this, it.title.toString() + "重复选择", Toast.LENGTH_SHORT).show()
        }


        val subscribe = RetrofitApi.apiService
            .rankCategory()
            .compose(RxSchedulers.compose())
            .subscribe(object : TObserver<Find>() {
                override fun onSuccess(it: Find) {
                    if (it.ok) {
                        LogUtils.e(it.male.first().title)
                    } else {
                        println("失败了")
                    }
                }
            })


        val constraintSet = ConstraintSet()
        //constraintSet.clone()

    }

    override fun initData(savedInstanceState: Bundle?) {
        mPager?.onDataLoading(LoadingPager.LoadedResult.SUCCESS)
    }

    fun splash(view:View) {
        val intent = Intent(this, SplashActivity::class.java)
        startActivity(intent)
    }
}
