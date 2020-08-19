package cn.huangchengxi.jetpack.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.huangchengxi.jetpack.R

class TransitionTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_two)
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }
}