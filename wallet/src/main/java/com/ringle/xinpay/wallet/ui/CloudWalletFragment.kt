package com.ringle.xinpay.wallet.ui

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_cloud_wallet_login.*
import org.jetbrains.anko.info

/**
 * create by 岑胜德
 * on 2019/12/10
 * 说明:
 *
 */
class CloudWalletFragment: BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_cloud_wallet_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        info { "onActivityCreated执行" }
    }

    override fun onStart() {
        super.onStart()
        info { "onStart 执行" }
        val spanStr=SpannableString("还没有注册账号?立即去注册")
        val foreColor=ForegroundColorSpan(Color.BLUE)
        spanStr.setSpan(foreColor,8,13,Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        tv_cloud_wallect_register.text = spanStr
        tv_cloud_wallect_register.setOnClickListener {
            //跳转注册页c
            /**
             *注意:navController当前的Fragment是WalletFragment,必须用其内部的Action跳转
             */
//            (activity as MainActivity).currentNavController?.observe(this, Observer { navController->
//                navController.navigate(R.id.action_tab_wallet_to_regist)
//
//            })
        }

    }

    override fun onStop() {
        super.onStop()
        info { "onStop 执行" }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        info { "onDestroyView 执行" }
    }
}