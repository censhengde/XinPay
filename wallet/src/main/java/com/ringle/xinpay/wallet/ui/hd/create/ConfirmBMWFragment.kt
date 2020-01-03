package com.ringle.xinpay.wallet.ui.hd.create

import android.os.Bundle
import android.view.View

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.popBackStack
import kotlinx.android.synthetic.main.fragment_backup_mnemonic_word.*


class ConfirmBMWFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_backup_mnemonic_word
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_backup_mnemonic_word.setOnClickListener {
            onClickBackup()
        }
        tv_tink_again.setOnClickListener {
            onClickThinkAgain()
        }

    }

    /**
     *点击"备份助记词"
     */
    private fun onClickBackup() {
        //弹框确认密码

    }

    /**
     *点击"想想再说"
     */
    private fun onClickThinkAgain() {
      popBackStack()
    }
}
