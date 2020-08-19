package cn.huangchengxi.jetpack.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Build

val PERMISSIONS= arrayOf(
    Manifest.permission.WRITE_EXTERNAL_STORAGE,
    Manifest.permission.READ_EXTERNAL_STORAGE
)

fun checkMissingPermissions(context: Context):ArrayList<String>{
    val arr=ArrayList<String>()
    if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M) return arr
    for (p in PERMISSIONS){
        if (context.checkSelfPermission(p)!=PackageManager.PERMISSION_GRANTED){
            arr.add(p)
        }
    }
    return arr
}
fun requestMissingPermissions(context: Context,requestCode:Int){
    if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M) return
    val missing= checkMissingPermissions(context)
    (context as Activity).requestPermissions(missing.toTypedArray(),requestCode)
}