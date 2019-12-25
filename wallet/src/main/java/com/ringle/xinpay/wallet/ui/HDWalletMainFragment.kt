package com.ringle.xinpay.wallet.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.base.navigate
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.adapter.CoinListAdapter
import com.ringle.xinpay.wallet.bean.ItemCoinList
import kotlinx.android.synthetic.main.fragment_base_deal.*
import kotlinx.android.synthetic.main.fragment_hdwallet_main.*
import org.consenlabs.tokencore.wallet.Identity
import java.util.*


class HDWalletMainFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hdwallet_main
    private val mCoinListDatas = ArrayList<ItemCoinList>()
    private lateinit var mIdentity: Identity

    override fun initData() {
        //1,拿到身份
        LiveDataBus.getChannel("identity").observe(this, Observer {
            mIdentity = it as Identity
        })

    }

    /**
     *创建各个币种事务
     */
    private fun initTransactions(){
//        val btcTran=BitcoinTransaction()

    }
    private fun initAsset() {
        //得到以太坊钱包
        val ethereumWallet = mIdentity.wallets[0]
        //比特币钱包
        val bitcoinWallet = mIdentity.wallets[1]
//        WalletManager.
        bitcoinWallet.encXPub
    }

    private fun initMenu() {
        //转账
        btn_wallet_deal_transfer.setOnClickListener {

        }
        //收款
        btn_wallet_deal_receive.setOnClickListener {

        }
    }

    /**
     *初始化币种列表
     */
    private fun initCoinList() {
        rv_coin_list.layoutManager = LinearLayoutManager(activity)
        val adapter = CoinListAdapter( R.layout.item_coin_list, mCoinListDatas)
        adapter.setOnItemClickListener{ad,v,position->
            //跳转交易页面
            val name = mCoinListDatas[position].name
            val args = Bundle()
            args.putString("COIN_NAME", name)
            navigate(R.id.action_HDWalletLoginedFragment_to_baseDealFragment, args)
        }



        rv_coin_list.adapter = adapter


    }
}
