package com.ringle.xinpay.wallet.ui

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View

import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.base.navigate
import kotlinx.android.synthetic.main.fragment_hd_wallet_default.*

/**
 * create by 岑胜德
 * on 2019/12/10
 * 说明:
 *
 */
class HDWalletFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hd_wallet_default


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spanStr = SpannableString("已有HD钱包?恢复钱包")
        val foreColor = ForegroundColorSpan(Color.BLUE)
        spanStr.setSpan(foreColor, 7, 11, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        tv_hd_wallet_recover_wallet.text = spanStr
        tv_hd_wallet_recover_wallet.setOnClickListener {
            //跳转注册页c
            navigate(R.id.action_to_hd_wallet_recover)

        }
        btn_to_hd_wallet_default.setOnClickListener {
            navigate(R.id.action_to_hd_wallet_create_frag)
        }
    }
}