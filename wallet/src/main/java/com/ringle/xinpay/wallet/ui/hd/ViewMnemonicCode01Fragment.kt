package com.ringle.xinpay.wallet.ui.hd

import androidx.lifecycle.Observer
import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.global.KEY_IDENTITY
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.wallet.global.KEY_MNEMONIC_CODE
import com.ringle.xinpay.wallet.ui.common.showInputDialog
import com.ringle.xinpay.wallet.ui.common.splitMnemonic
import kotlinx.android.synthetic.main.fragment_view_mnemonic_code01.*
import org.consenlabs.tokencore.wallet.Identity
import org.consenlabs.tokencore.wallet.Wallet
import org.consenlabs.tokencore.wallet.WalletManager
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast


class ViewMnemonicCode01Fragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_view_mnemonic_code01
    }


    private lateinit var mWallet: Wallet
    private lateinit var mIdentity: Identity
    override fun initData() {
        initActionBar(title = "查看助记词", onClickBack = {
            toast("back")
            navigate(R.id.action_back_out_graph_view_mnemonic_code)
        })
        btn_view_mnemonic_code.setOnClickListener {
            //弹框,输入密码
            showInputDialog(activity!!, confirm = { password ->
                //校验密码
                if (!checkPassword(password)) return@showInputDialog
                try {
                    val mnemonicCode = Identity.getCurrentIdentity().exportIdentity(password)
                    info { "助记词:${mnemonicCode}" }
                    val mnemonicCodes = splitMnemonic(mnemonicCode)
                    //发送数据
                    LiveDataBus.getChannel(KEY_MNEMONIC_CODE).value = mnemonicCodes
                    navigate(R.id.action_viewMnemonicCode01Fragment_to_viewMnemonicCode02Fragment)
                } catch (e: Exception) {
                    toast("密码不正确")
                    error { e.toString() }
                    return@showInputDialog
                }


            })
        }
    }


    override fun onStart() {
        super.onStart()
        LiveDataBus.getChannel(KEY_IDENTITY).observe(this, Observer {
            mIdentity = it as Identity
//            mWallet = identity.wallets[0]

        })
    }

    private fun checkPassword(password: String): Boolean {
        if (password.isEmpty()) {
            toast("输入不能为空")
            return false
        }
        return true
    }
}
