package cn.huangchengxi.jetpack.animation

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.huangchengxi.jetpack.R
import kotlinx.android.synthetic.main.activity_transition_one.*

class TransitionOneActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition_one)
        androidImg.setOnClickListener {
            val intent= Intent(this,TransitionTwoActivity::class.java)
            val options=ActivityOptions.makeSceneTransitionAnimation(this,androidImg,"androidImg")
            startActivity(intent,options.toBundle())
        }
    }
}