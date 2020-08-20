package cn.huangchengxi.jetpack.application

import android.app.Application
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import cn.huangchengxi.jetpack.IAudioManager
import cn.huangchengxi.jetpack.audiomedia.AudioPlayService

class JApplication:Application(),ServiceConnection {
    private lateinit var audioManager: IAudioManager

    override fun onCreate() {
        super.onCreate()
        val intent= Intent(this, AudioPlayService::class.java)
        bindService(intent,this, Context.BIND_AUTO_CREATE)
    }

    override fun onTerminate() {
        super.onTerminate()
        unbindService(this)
    }

    override fun onServiceDisconnected(p0: ComponentName?) {

    }

    override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
        audioManager=IAudioManager.Stub.asInterface(p1)
    }
}