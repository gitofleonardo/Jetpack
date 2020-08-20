package cn.huangchengxi.jetpack.audiomedia

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import cn.huangchengxi.jetpack.IAudioManager
import cn.huangchengxi.jetpack.R
import cn.huangchengxi.jetpack.databinding.AudioDataBinding
import kotlinx.android.synthetic.main.activity_audio.*

class AudioActivity : AppCompatActivity(),ServiceConnection{
    private val model by viewModels<AudioModel>()
    private lateinit var audioManager:IAudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_audio)
        val binding=DataBindingUtil.setContentView<AudioDataBinding>(this,R.layout.activity_audio)
        binding.model=model
        audioControlBtn.setOnClickListener {
            when (audioManager.isPlaying){
                true->{
                    audioManager.stopAudio()
                    model.imgSrc.set(R.drawable.play)
                }
                false->{
                    audioManager.playAudio()
                    model.imgSrc.set(R.drawable.stop)
                }
            }
        }
    }

    override fun onServiceDisconnected(p0: ComponentName?) {

    }

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        audioManager=IAudioManager.Stub.asInterface(p1)
        when (audioManager.isPlaying){
            true->{
                model.imgSrc.set(R.drawable.stop)
            }
            false->{
                model.imgSrc.set(R.drawable.play)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val intent=Intent(this,AudioPlayService::class.java)
        bindService(intent,this, Context.BIND_AUTO_CREATE)
    }
    override fun onStop() {
        super.onStop()
        unbindService(this)
    }
}