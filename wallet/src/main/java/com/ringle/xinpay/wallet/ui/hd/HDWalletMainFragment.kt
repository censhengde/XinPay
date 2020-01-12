package com.ringle.xinpay.wallet.ui.hd

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.findNavController
import com.ringle.xinpay.common.extensions.navigate
import kotlinx.android.synthetic.main.fragment_hdwallet_main.*

/**
 *HDWallet主界面(已创建)
 */
class HDWalletMainFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hdwallet_main


    override fun initData() {
        initOnClickListener()

    }


    private fun initOnClickListener() {
        //点击转账
        rb_hd_wallet_main_send.setOnClickListener {v->
            navigate(R.id.action_frag_wallet_main_to_frag_hdwallet_transation_send)
        }
        //点击收款
        rb_hd_wallet_main_receive.setOnClickListener {v->
//           findNavController(v).navigate()
            navigate(R.id.action_frag_wallet_main_to_frag_hdwallet_transation_recieve)
        }
    }
}
