package com.ringle.xinpay.wallet.ui.common

import android.widget.ImageView
import android.widget.TextView
import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment

/**
 * create by 岑胜德
 * on 2019/12/19
 * 说明:
 *
 */
open class TransactionFragment : BaseFragment() {
    override fun setContentView(): Int {

        return R.layout.fragment_base_deal
    }


    override fun initData() {
        val coinName = arguments?.getString("COIN_NEME")
       coinName?.let {
        initActionBar(title = coinName)
       }
    }

//    protected open fun initActionBar(lResId: Int = 0, rResId: Int = 0, title: String = "") {
//        if (lResId != 0) activity?.findViewById<ImageView>(R.id.iv_common_action_bar_left)?.setImageResource(
//            lResId
//        )
//        if (title != "") activity?.findViewById<TextView>(R.id.tv_common_action_bar_center_title)
//            ?.text = title
//        if (rResId != 0) activity?.findViewById<ImageView>(R.id.iv_common_action_bar_right)?.setImageResource(
//            rResId
//        )
//    }
}