package com.ringle.xinpay.ui.fragment

import android.net.Uri
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.app.R
import kotlinx.android.synthetic.main.fragment_user_info.*

/**
 * create by 岑胜德
 * on 2019/12/30
 * 说明:
 *
 */
class UserInfoFragment: BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_user_info
    }


    override fun initData() {

        tv_user_info_view_mnemonic_code.setOnClickListener {
            //跳转
            val deeplink = Uri.parse("http://www.com.ringle.xinpay")
//            navigate(deeplink)
        }
    }
}