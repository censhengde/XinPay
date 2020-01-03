package com.ringle.xinpay.wallet.ui.hd

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.adapter.CoinListAdapter
import com.ringle.xinpay.wallet.bean.ItemCoinList
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.global.KEY_GLOBAL_NAV_CONTROLLER
import com.ringle.xinpay.wallet.global.KEY_IDENTITY
import kotlinx.android.synthetic.main.fragment_base_deal.*
import kotlinx.android.synthetic.main.fragment_hdwallet_main.*
import org.consenlabs.tokencore.wallet.Identity
import java.util.*

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

            navigate("http://www.ringle.com/xinpay/HDWalletSendFragment")
        }
    }
}
