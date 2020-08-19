package cn.huangchengxi.jetpack.animation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.core.animation.addListener
import cn.huangchengxi.jetpack.R
import kotlinx.android.synthetic.main.activity_circular_animation.*
import kotlin.math.hypot

class CircularAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_animation)
        btn.setOnClickListener {
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                val cardWidth=card.width
                val cardHeight=card.height
                val radius= hypot(cardWidth.toDouble()/2,cardHeight.toDouble()).toFloat()
                if (card.visibility==View.VISIBLE){
                    val anim=ViewAnimationUtils.createCircularReveal(card,cardWidth/2,cardHeight,radius,0.0f)
                    anim.addListener(onEnd = {
                        card.visibility=View.GONE
                    })
                    anim.start()
                }else{
                    val anim=ViewAnimationUtils.createCircularReveal(card,cardWidth/2,cardHeight,0.0f,radius)
                    anim.start()
                    card.visibility=View.VISIBLE
                }
            }else{
                if (card.visibility==View.VISIBLE){
                    card.visibility=View.GONE
                }else{
                    card.visibility=View.VISIBLE
                }
            }
        }
    }
}