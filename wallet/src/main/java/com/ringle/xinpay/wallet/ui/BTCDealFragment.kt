package com.ringle.xinpay.wallet.ui


import com.ringle.wallet.R
import com.ringle.xinpay.wallet.ui.base.TransactionFragment
import kotlinx.android.synthetic.main.fragment_base_deal.*


class BTCDealFragment : TransactionFragment() {


    override fun initData() {
        initActionBar(R.drawable.md_nav_back, title = "BTC")
        //
        btn_wallet_deal_transfer.setOnClickListener {

        }
        btn_wallet_deal_receive.setOnClickListener {
            
        }
    }
}
