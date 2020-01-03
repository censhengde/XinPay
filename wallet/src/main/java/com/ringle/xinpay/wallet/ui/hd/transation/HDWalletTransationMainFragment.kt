package com.ringle.xinpay.wallet.ui.hd.transation


import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.wallet.R

/**
 *HDWallet交易
 */
class HDWalletTransationMainFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hdwallet_transation_main

    override fun initData() {
        initActionBar(title = "BTC")
    }
    /**
     *初始化余额
     */
    private fun initBalance() {

    }

    /**
     *初始化交易记录
     */
    private fun initTransationRecord() {


    }

    /**
     *点击转账
     */
    private fun onClickSend(){

    }
    /**
     *点击收款
     */
    private fun onClickRecieve(){

    }
}
