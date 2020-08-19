package cn.huangchengxi.jetpack.animation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.dynamicanimation.animation.FloatValueHolder
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import cn.huangchengxi.jetpack.R
import kotlinx.android.synthetic.main.activity_spring_animation.*

class SpringAnimationActivity : AppCompatActivity() {
    private var startX=0.0f
    private var startY=0.0f
    private lateinit var animationX:SpringAnimation
    private lateinit var animationY:SpringAnimation
    private var initDist=500.0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spring_animation)
        dragBtn.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN->{
                    startX=motionEvent.rawX
                    startY=motionEvent.rawY
                }
                MotionEvent.ACTION_MOVE->{
                    val distX=motionEvent.rawX-startX
                    val distY=motionEvent.rawY-startY
                    view.layout(view.left+distX.toInt(),view.top+distY.toInt(),view.right+distX.toInt(),view.bottom+distY.toInt())
                    startX=motionEvent.rawX
                    startY=motionEvent.rawY
                    animationX.animateToFinalPosition(view.left.toFloat())
                    animationY.animateToFinalPosition(view.top.toFloat()+initDist)
                }
            }
            true
        }
        animationX=SpringAnimation(followerBtn,SpringAnimation.X).apply {
            spring= SpringForce()
            spring.dampingRatio=SpringForce.DAMPING_RATIO_LOW_BOUNCY
            spring.stiffness=SpringForce.STIFFNESS_VERY_LOW
        }
        animationY= SpringAnimation(followerBtn,SpringAnimation.Y).apply {
            spring=SpringForce()
            spring.dampingRatio=SpringForce.DAMPING_RATIO_LOW_BOUNCY
            spring.stiffness=SpringForce.STIFFNESS_VERY_LOW
        }
    }
}