package com.ringle.xinpay.ui.fragment.tab

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.app.R
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_tab_home.*

/**
 *create by 岑胜德
 *on 2019/10/31
 *说明:
 *
 */
class HomeFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_tab_home
//    private val mAdapter=


    override fun onStart() {
        super.onStart()
        initView()
        initActionBar()
    }

    private fun initView() {
        //轮播图
        val banners_path = listOf(
            R.drawable.home_banner01,
            R.drawable.home_banner02,
            R.drawable.home_banner03
        )
        banner_home.setImages(banners_path)
        banner_home.setImageLoader(object : ImageLoader() {
            override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
                Glide.with(context!!.applicationContext)
                    .load(path)
                    .into(imageView!!);
            }

        })
        banner_home.start()

        //滚动通知栏
        val notifys = listOf(
            "Wallet已支持本次ETH伊斯坦布尔升级",
            "关于币币交易减免手续费通知",
            "Wallet新版本即将发布"
        )
        mqv_home_notify.startWithList(notifys as List<Nothing>?)

    }


    private fun initActionBar(){
        val tv=activity?.findViewById<TextView>(R.id.action_bar_home_user_avatar)
        tv?.setOnClickListener {
           //跳转
            navigate(R.id.action_home_to_userInfoFragment)
        }
    }
    override fun onStop() {
        super.onStop()
        banner_home.stopAutoPlay()
    }
}