package com.ringle.xinpay.ui.fragment.tab

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ringle_al.common.base.BaseFragment
import com.ringle.wallet.R
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
                Glide.with(context!!.getApplicationContext())
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


    override fun onStop() {
        super.onStop()
        banner_home.stopAutoPlay()
    }
}