package cn.huangchengxi.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import cn.huangchengxi.jetpack.databinding.MainViewBinding
import cn.huangchengxi.jetpack.utils.checkMissingPermissions
import cn.huangchengxi.jetpack.utils.requestMissingPermissions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS=0
    private val rootCoorView by lazy { findViewById<View>(android.R.id.content) }
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=DataBindingUtil.setContentView<MainViewBinding>(this,R.layout.activity_main)
        binding.lifecycleOwner=this
        binding.model=viewModel
        homeRecyclerview.itemAnimator=DefaultItemAnimator().apply {
            addDuration=200
        }
        if (checkMissingPermissions(this).size>0)
            requestMissingPermissions(this,REQUEST_PERMISSIONS)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            REQUEST_PERMISSIONS->{
                if (checkMissingPermissions(this).size>0){
                    showRequestAlert()
                }
            }
        }
    }
    private fun showRequestAlert(){
        val snack=Snackbar.make(rootCoorView!!,"部分权限未授予",Snackbar.LENGTH_SHORT)
        snack.setAction("授权"){
            requestMissingPermissions(this,REQUEST_PERMISSIONS)
            snack.dismiss()
        }
        snack.show()
    }
}