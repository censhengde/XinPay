package com.ringle.xinpay.wallet.ui.hd


import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.adapter.MnemonicListAdapter
import com.ringle.xinpay.wallet.bean.MnemonicCode
import com.ringle.xinpay.wallet.global.KEY_MNEMONIC_CODE
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.extensions.popBackStack
import kotlinx.android.synthetic.main.fragment_browse_bmw.*
import kotlinx.android.synthetic.main.fragment_view_mnemonic_code02.*
import org.jetbrains.anko.support.v4.toast
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 */
@Suppress("UNCHECKED_CAST")
class ViewMnemonicCode02Fragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_view_mnemonic_code02
    }

    private lateinit var mMnemonicCode: ArrayList<MnemonicCode>


    private lateinit var mnemonicListAdapter: MnemonicListAdapter


    override fun initData() {
        initActionBar(title = "查看助记词", onClickBack = {
            popBackStack()
        })
        rv_view_mnemonic_code_list.layoutManager = GridLayoutManager(activity, 4)
        mnemonicListAdapter = MnemonicListAdapter(layoutResId = R.layout.item_mnemonic_list)
        rv_view_mnemonic_code_list.adapter = mnemonicListAdapter
        btn_finish.setOnClickListener {
            //跳转首页
            navigate(R.id.action_back_out_graph_view_mnemonic_code)
        }
    }

    override fun onStart() {
        super.onStart()
        LiveDataBus.getChannel(KEY_MNEMONIC_CODE).observe(this, Observer {
            val s = it as ArrayList<MnemonicCode>
            mMnemonicCode = s
            mnemonicListAdapter.data = s

        })

    }


}
