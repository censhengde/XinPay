package com.ringle.xinpay.wallet.ui


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View

import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.base.navigate
import com.ringle.xinpay.common.base.popBackStack
import com.ringle.xinpay.common.util.LiveDataBus
import kotlinx.android.synthetic.main.fragment_create_wallet.*
import org.bitcoinj.crypto.MnemonicCode
import org.consenlabs.tokencore.wallet.Identity
import org.consenlabs.tokencore.wallet.model.Metadata
import org.consenlabs.tokencore.wallet.model.Network
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class HDWalletCreateFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_create_wallet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_create_wallet_back.setOnClickListener {
            popBackStack()
        }
    }

    override fun initData() {
        //点击
        btn_create_wallet.setOnClickListener {
            //密码
            val password = et_input_password.text.toString()
            //确认密码
            val confirmPassword = et_confirm_password.text.toString()
            //检查密码
            if (!checkPassword(password, confirmPassword)) {
                return@setOnClickListener
            }
            //创建身份
            val identity = Identity.createIdentity(
                "MyFirstIdentity",
                confirmPassword,
                "PASSWORD_HINT",
                Network.TESTNET,
                Metadata.HD
            )
            //导出助记词
            val mnemonicWord = identity.exportIdentity(confirmPassword)
            //得到以太坊钱包
            val ethereumWallet = identity.wallets[0]
            //比特币钱包
            val bitcoinWallet = identity.wallets[1]
            info { "助记词==$mnemonicWord,钱包==${identity.wallets.size}" }

            //发送数据
            LiveDataBus.getChannel("identity").value = identity
//            LiveDataBus.getChannel("mnemonic").value = mnemonicWord
//            LiveDataBus.getChannel("ethereumWallet").value = ethereumWallet
//            LiveDataBus.getChannel("bitcoinWallet").value = bitcoinWallet
            //跳转页面
            navigate(R.id.action_create_to_browse)


        }

    }

    /**
     *检查密码
     */
    private fun checkPassword(password: String, confirmPassword: String): Boolean {

        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            toast("输入不能为空")
            return false
        }
        if (password != confirmPassword) {
            toast("两次密码输入不一致")
            return false
        }
        return true
    }
}
