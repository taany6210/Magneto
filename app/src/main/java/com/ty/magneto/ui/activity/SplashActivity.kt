package com.ty.magneto.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.ty.magneto.R

/**
 * @ 创建者:   ty
 * @ 时间:    2019/1/23 10:27
 * @ 描述:
 */
class SplashActivity : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var constraintSet: ConstraintSet
    private lateinit var constraintSet2: ConstraintSet
    private var show: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        constraintLayout = findViewById(R.id.cl_root)
        constraintSet = ConstraintSet()
        constraintSet2 = ConstraintSet()

        constraintSet.clone(this, R.layout.activity_splasha)
        constraintSet2.clone(constraintLayout)

        TransitionManager.beginDelayedTransition(constraintLayout)

    }

    fun play(view: View) {
        if (show) {
            constraintSet2.applyTo(constraintLayout)
        } else {
            constraintSet.applyTo(constraintLayout)

        }

        show = !show
    }

}