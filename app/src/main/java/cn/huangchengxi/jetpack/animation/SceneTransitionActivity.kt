package cn.huangchengxi.jetpack.animation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.transition.AutoTransition
import androidx.transition.Scene
import androidx.transition.TransitionManager
import cn.huangchengxi.jetpack.R
import kotlinx.android.synthetic.main.activity_scene_transition.*
import kotlinx.android.synthetic.main.scene_one.sceneImg

class SceneTransitionActivity : AppCompatActivity() {
    private val sceneOne by lazy { Scene.getSceneForLayout(sceneRoot,R.layout.scene_one,this) }
    private val sceneTwo by lazy { Scene.getSceneForLayout(sceneRoot,R.layout.scene_two,this) }
    private var switch=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene_transition)

        sceneRoot.findViewById<View>(R.id.sceneImg).setOnClickListener {
            val transition=AutoTransition()
            TransitionManager.go(sceneTwo,transition)
        }
        sceneTwo.setEnterAction {
            switch=true
            sceneRoot.findViewById<View>(R.id.sceneImg).setOnClickListener {
                val transition=AutoTransition()
                TransitionManager.go(sceneOne,transition)
            }
        }
        sceneOne.setEnterAction {
            switch=false
            sceneRoot.findViewById<View>(R.id.sceneImg).setOnClickListener {
                val transition=AutoTransition()
                TransitionManager.go(sceneTwo,transition)
            }
        }
    }

    override fun onBackPressed() {
        if (switch){
            val transition=AutoTransition()
            TransitionManager.go(sceneOne,transition)
            return
        }
        super.onBackPressed()
    }
}