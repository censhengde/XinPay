package com.ringle_al.wallet.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ringle_al.common.base.BaseFragment
import com.ringle_al.wallet.R
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *create by 岑胜德
 *on 2019/10/31
 *说明:
 *
 */
class HomeFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_home
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }


}