package cn.huangchengxi.jetpack.animation

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import cn.huangchengxi.jetpack.R
import kotlinx.android.synthetic.main.activity_animation_main.*

class AnimationMainActivity : AppCompatActivity() {
    private lateinit var animationDrawableBg:AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_main)
        animationDrawable.apply {
            setBackgroundResource(R.drawable.d_animation_1)
            animationDrawableBg=background as AnimationDrawable
        }
        animationDrawable.setOnClickListener {
            if (animationDrawableBg.isRunning){
                animationDrawableBg.stop()
            }else{
                animationDrawableBg.start()
            }
        }
        dialogBtn.setOnClickListener {
            AlertDialog.Builder(this,R.style.CustomDialogTheme)
                .setTitle("Custom Dialog Animation")
                .setPositiveButton("Sure"){ dialogInterface, _ ->
                    dialogInterface.dismiss()
                }
                .setCancelable(true)
                .show()
        }
        circularAnimationBtn.setOnClickListener {
            startActivity(Intent(this,CircularAnimationActivity::class.java))
        }
        springAnimationBtn.setOnClickListener {
            startActivity(Intent(this,SpringAnimationActivity::class.java))
        }
        sceneChangeBtn.setOnClickListener {
            startActivity(Intent(this,SceneTransitionActivity::class.java))
        }
        activityTransitionBtn.setOnClickListener {
            startActivity(Intent(this,TransitionOneActivity::class.java))
        }
    }
}