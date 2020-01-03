package com.ringle.xinpay.wallet.ui.hd.transation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.extensions.popBackStack

import com.ringle.xinpay.wallet.R

/**
 * A simple [Fragment] subclass.
 */
class HDWalletSendFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hdwallet_send

    override fun initData() {
        initActionBar(
            title = "BTC转账",
            titleLogo = R.drawable.resources_img_icon_arrow_down,
            onClickTitle = {
            //跳转更换币种页
             navigate(R.id.action_frag_hdwallet_transation_send_to_frag_hdwallet_transation_choose_coin)
            },
            onClickBack = {
//            popBackStack()
                navigate(R.id.action_back_out_graph_hd_wallet_transation)

            })
    }
}
