package com.ringle.wallet.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ringle.wallet.R
import com.ringle_al.common.base.BaseFragment
import com.ringle_al.common.base.popBackStack
import kotlinx.android.synthetic.main.fragment_recover_wallet.*

/**
 * A simple [Fragment] subclass.
 */
class RecoverWalletFragment : BaseFragment() {
    override fun setContentView(): Int=R.layout.fragment_recover_wallet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_recover_wallet_back.setOnClickListener {
            popBackStack()
        }
    }

}
