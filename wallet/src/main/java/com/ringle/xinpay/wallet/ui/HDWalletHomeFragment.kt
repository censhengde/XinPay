package com.ringle.xinpay.wallet.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ringle.xinpay.common.extensions.navigate

import com.ringle.xinpay.wallet.R
import kotlinx.android.synthetic.main.fragment_home.*


/**
  *模拟首页
  */
class HDWalletHomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

     override fun onActivityCreated(savedInstanceState: Bundle?) {
         super.onActivityCreated(savedInstanceState)
         tv_hdwallet_home_view_mnemonic_code.setOnClickListener {
             //跳转查看助记词页面
             navigate(R.id.action_to_view_mnemonic)
         }

         tv_hdwallet_home_import_hdwallet.setOnClickListener {
             //跳转导入钱包页面
             navigate(R.id.action_to_hd_wallet_import)
         }
     }

}
