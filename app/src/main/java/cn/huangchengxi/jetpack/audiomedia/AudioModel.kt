package cn.huangchengxi.jetpack.audiomedia

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import cn.huangchengxi.jetpack.R
import com.bumptech.glide.Glide

class AudioModel:ViewModel() {
    var imgSrc= ObservableInt(R.drawable.play)

    companion object{
        @JvmStatic
        @BindingAdapter("urlSrc")
        fun loadImage(view: ImageView, url:Int){
            Glide.with(view.context).load(url).into(view)
        }
    }
}