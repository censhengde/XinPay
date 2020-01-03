package com.ringle.xinpay.wallet.ui.hd.create

import com.ringle.xinpay.wallet.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.adapter.MnemonicListAdapter
import com.ringle.xinpay.wallet.bean.MnemonicCode
import com.ringle.xinpay.wallet.global.KEY_MNEMONIC_CODE
import com.ringle.xinpay.common.extensions.navigate
import kotlinx.android.synthetic.main.fragment_browse_bmw.*
import kotlinx.android.synthetic.main.fragment_browse_bmw.rv_mnemonic_code_list
import kotlin.collections.ArrayList

/**
 * create by 岑胜德
 * on 2019/12/16
 * 说明:备份助记词
 */
@Suppress("UNCHECKED_CAST")
class HDWalletBackupMnemonicFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_browse_bmw
    }

    //助记词
    private lateinit var mMnemonicCodes :ArrayList<MnemonicCode>



    override fun onStart() {
        super.onStart()
        //拿到助记词
        LiveDataBus.getChannel(KEY_MNEMONIC_CODE).observe(this, Observer {
           mMnemonicCodes=it as ArrayList<MnemonicCode>
        })

    }

    override fun onResume() {
        super.onResume()
        rv_mnemonic_code_list.layoutManager = GridLayoutManager(activity, 4)
        val adapter = MnemonicListAdapter(mMnemonicCodes, R.layout.item_mnemonic_list)
        rv_mnemonic_code_list.adapter = adapter
        //跳转页面
        btn_next.setOnClickListener {
            navigate(R.id.action_browse_to_verify)
        }
    }


    override fun initData() {

    }



}
