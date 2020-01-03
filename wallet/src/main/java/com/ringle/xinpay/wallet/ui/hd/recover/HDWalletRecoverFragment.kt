package com.ringle.xinpay.wallet.ui.hd.recover


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.popBackStack
import kotlinx.android.synthetic.main.fragment_recover_wallet.*

/**
 * A simple [Fragment] subclass.
 */
class HDWalletRecoverFragment : BaseFragment() {
    override fun setContentView(): Int=R.layout.fragment_recover_wallet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_recover_wallet_back.setOnClickListener {
            popBackStack()
        }
    }

}
