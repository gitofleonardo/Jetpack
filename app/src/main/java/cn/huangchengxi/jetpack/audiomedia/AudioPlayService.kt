package cn.huangchengxi.jetpack.audiomedia

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import cn.huangchengxi.jetpack.IAudioManager
import cn.huangchengxi.jetpack.R

class AudioPlayService : Service() {
    private lateinit var binder:IBinder
    private val audioManager=AudioManager()
    private val audioController by lazy { MediaPlayer.create(this,R.raw.happybirthday) }

    override fun onCreate() {
        super.onCreate()
        binder=audioManager.asBinder()
        audioController.isLooping=true
    }

    private inner class AudioManager:IAudioManager.Stub(){
        override fun stopAudio() {
            audioController.pause()
        }

        override fun playAudio() {
            audioController.start()
        }

        override fun isPlaying(): Boolean {
            return audioController.isPlaying
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        audioController.stop()
    }
}
