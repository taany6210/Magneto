package com.ty.magneto

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.ty.magneto.bean.User
import com.ty.magneto.databinding.ActivityMainBinding

/**
 * @ 创建者:   ty
 * @ 时间:    2018/10/18 15:51
 * @ 描述:    main
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}
