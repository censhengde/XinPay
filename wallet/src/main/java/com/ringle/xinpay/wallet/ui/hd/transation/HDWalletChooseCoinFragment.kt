package com.ringle.xinpay.wallet.ui.hd.transation


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate

import com.ringle.xinpay.wallet.R
import kotlinx.android.synthetic.main.fragment_hdwallet_choose_coin.*

/**
 *更换币种
 */
class HDWalletChooseCoinFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hdwallet_choose_coin

    override fun initData() {
        initActionBar(title = "更换币种",
            onClickBack = {
            navigate(R.id.action_back_to_frag_hdwallet_transation_recieve)
        })
    }


    private fun initCoinList(){
//        rv_hdwallet_transation_choose_coin_list
    }
}
