package com.ringle.xinpay.ui.fragment.wallet

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.wallet.R
import com.ringle.xinpay.ui.activity.MainActivity
import kotlinx.android.synthetic.main.fragment_hd_wallet_create.*
import androidx.lifecycle.Observer

/**
 * create by 岑胜德
 * on 2019/12/10
 * 说明:
 *
 */
class HDWalletFragment:BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hd_wallet_create


    override fun onStart() {
        super.onStart()
        val spanStr= SpannableString("已有HD钱包?恢复钱包")
        val foreColor= ForegroundColorSpan(Color.BLUE)
        spanStr.setSpan(foreColor,7,11, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        tv_hd_wallet_recover_wallet.text = spanStr
        tv_hd_wallet_recover_wallet.setOnClickListener {
            //跳转注册页c
            /**
             *注意:navController当前的Fragment是WalletFragment,必须用其内部的Action跳转
             */
            (activity as MainActivity).currentNavController?.observe(this, Observer { navController->
                navController.navigate(R.id.action_tab_wallet_to_create_wallet)

            })
        }
    }
}