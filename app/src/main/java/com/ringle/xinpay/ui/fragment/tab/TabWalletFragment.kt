package com.ringle.xinpay.ui.fragment.tab

import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.ui.activity.MainActivity
import com.ringle.xinpay.app.R

/**
 * create by 岑胜德
 * on 2019/12/27
 * 说明:
 *
 */
class TabWalletFragment:BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_tab_wallet
    }



    override fun onStart() {
        super.onStart()
//       navigate(R.id.action_to_wallet_module)
    }
}