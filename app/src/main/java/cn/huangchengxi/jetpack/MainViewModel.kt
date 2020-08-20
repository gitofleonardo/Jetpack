package cn.huangchengxi.jetpack

import android.content.Intent
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cn.huangchengxi.jetpack.animation.AnimationMainActivity
import cn.huangchengxi.jetpack.audiomedia.AudioActivity
import cn.huangchengxi.jetpack.camerax.CameraXActivity
import cn.huangchengxi.jetpack.notification.NotificationActivity
import com.bumptech.glide.Glide

class MainViewModel:ViewModel() ,View.OnClickListener{
    val text=MutableLiveData<String>()
    val items=ObservableArrayList<Item>()
    val adapter=HomeAdapter(items)
    val layoutManager=StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
    init {
        items.add(Item(R.drawable.camera,"CameraX"))
        items.add(Item(R.drawable.animations,"Animations"))
        items.add(Item(R.drawable.media,"MediaController"))
        items.add(Item(R.drawable.notification,"Notifications"))
    }
    companion object{
        @JvmStatic
        @BindingAdapter("urlSrc")
        fun loadImage(view:ImageView,url:String){
            Glide.with(view.context).load(url).placeholder(R.drawable.ic_launcher_background).into(view)
        }
        @JvmStatic
        @BindingAdapter("adapter")
        fun setRecyclerAdapter(view:RecyclerView,adapter:RecyclerView.Adapter<RecyclerView.ViewHolder>){
            view.adapter=adapter
        }
    }
    class HomeAdapter(private val items:ObservableArrayList<Item>):RecyclerView.Adapter<Holder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            return Holder(LayoutInflater.from(parent.context).inflate(R.layout.view_home_item,parent,false))
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val context=holder.icon.context

            holder.title.text=SpannableStringBuilder(items[position].title)
            Glide.with(holder.icon.context).load(items[position].icon).into(holder.icon)
            holder.view.setOnClickListener {
                when (position){
                    0->{
                        context.startActivity(Intent(context, CameraXActivity::class.java))
                    }
                    1->{
                        context.startActivity(Intent(context,AnimationMainActivity::class.java))
                    }
                    2->{
                        context.startActivity(Intent(context,AudioActivity::class.java))
                    }
                    3->{
                        context.startActivity(
                            Intent(context, NotificationActivity::class.java)
                        )
                    }
                }
            }
        }
    }
    class Holder(val view:View):RecyclerView.ViewHolder(view){
        val title=view.findViewById<TextView>(R.id.title)
        val icon=view.findViewById<ImageView>(R.id.icon)
    }
    data class Item(@DrawableRes val icon:Int, val title:String)

    override fun onClick(p0: View?) {

    }
}