package cn.huangchengxi.jetpack.camerax

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import cn.huangchengxi.jetpack.R

@RequiresApi(value = Build.VERSION_CODES.LOLLIPOP)
class CameraXActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera_x)
    }
}