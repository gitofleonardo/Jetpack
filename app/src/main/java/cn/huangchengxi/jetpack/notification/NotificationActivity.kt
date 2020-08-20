package cn.huangchengxi.jetpack.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import cn.huangchengxi.jetpack.MainActivity
import cn.huangchengxi.jetpack.R
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    private val CHANNEL_NOTIFICATION="CHANNEL_NOTIFICATION"
    private val CHANNEL_NOTIFICATION_NAME="NOTIFY_CHANNEL"
    private val NOTIFICATION_ID=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        init()
    }
    private fun init(){
        simpleNotification.setOnClickListener {
            val n=NotificationCompat.Builder(this,CHANNEL_NOTIFICATION)
                .setSmallIcon(R.drawable.jetpack)
                .setContentTitle("SimpleNotification")
                .setContentText("As you can see, this is a simple notification")
                .setStyle(NotificationCompat.BigTextStyle().bigText("As you can see, this is a big text notification"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            createNotificationChannel(n.build())
        }
        simpleNotificationClick.setOnClickListener {
            val intent=Intent(this,NotificationActivity::class.java)
            //val pending=PendingIntent.getActivity(this,0,intent,0)
            val pending=TaskStackBuilder.create(this).apply {
                addNextIntentWithParentStack(intent)
            }
            val n=NotificationCompat.Builder(this,CHANNEL_NOTIFICATION)
                .setContentIntent(pending.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT))
                .setSmallIcon(R.drawable.jetpack)
                .setContentTitle("SimpleNotification")
                .setContentText("As you can see, this is a simple notification")
                .setStyle(NotificationCompat.BigTextStyle().bigText("As you can see, this is a big text notification"))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
            createNotificationChannel(n.build())
            //with(NotificationManagerCompat.from(this)){
            //    notify(NOTIFICATION_ID,n.build())
            //}
        }
    }
    private fun createNotificationChannel(notification:Notification){
        val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        with(manager){
            if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
                val channel=NotificationChannel(CHANNEL_NOTIFICATION,CHANNEL_NOTIFICATION_NAME,NotificationManager.IMPORTANCE_DEFAULT)
                createNotificationChannel(channel)
                notify(NOTIFICATION_ID,notification)
            }else{
                notify(NOTIFICATION_ID,notification)
            }
        }
    }
}