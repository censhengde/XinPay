package com.ringle.xinpay.ui.fragment.tab


import androidx.fragment.app.Fragment
import com.ringle.xinpay.ui.fragment.borrow.C2CBorrowFragment
import com.ringle.xinpay.ui.fragment.borrow.FlashBorrowFragment
import com.ringle.xinpay.ui.fragment.borrow.MatchBorrowFragment
import com.ringle_al.common.base.BaseFragment

import com.ringle.wallet.R

/**
 * A simple [Fragment] subclass.
 */
class BorrowFragment : WalletFragment() {
    override fun setContentView(): Int = R.layout.fragment_tab_borrow

    override val mPages by lazy(LazyThreadSafetyMode.NONE) {
        arrayListOf(
            C2CBorrowFragment(),
            MatchBorrowFragment(),
            FlashBorrowFragment()
        )
    }
    override val mTabTitles by lazy(LazyThreadSafetyMode.NONE) {
        arrayListOf(
            "C2C借贷",
            "撮合借贷",
            "闪电贷"
        )
    }
}
