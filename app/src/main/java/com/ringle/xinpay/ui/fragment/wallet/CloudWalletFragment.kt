package com.ringle.xinpay.ui.fragment.wallet

import android.os.Bundle
import com.ringle_al.common.base.BaseFragment
import com.ringle_al.wallet.R
import org.jetbrains.anko.info

/**
 * create by 岑胜德
 * on 2019/12/10
 * 说明:
 *
 */
class CloudWalletFragment:BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_cloud_wallet_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        info { "onActivityCreated执行" }
    }

    override fun onStart() {
        super.onStart()
        info { "onStart 执行" }
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