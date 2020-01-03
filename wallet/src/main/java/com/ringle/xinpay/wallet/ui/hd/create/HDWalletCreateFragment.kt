package com.ringle.xinpay.wallet.ui.hd.create


import android.os.Bundle
import android.text.TextUtils
import android.view.View

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.bean.MnemonicCode
import com.ringle.xinpay.wallet.global.KEY_IDENTITY
import com.ringle.xinpay.wallet.global.KEY_MNEMONIC_CODE
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.extensions.popBackStack
import com.ringle.xinpay.wallet.ui.common.splitMnemonic
import kotlinx.android.synthetic.main.fragment_hdwallet_create.*
import org.consenlabs.tokencore.foundation.utils.MnemonicUtil
import org.consenlabs.tokencore.wallet.Identity
import org.consenlabs.tokencore.wallet.model.Metadata
import org.consenlabs.tokencore.wallet.model.Network
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

/**
 *创建钱包
 */
class HDWalletCreateFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hdwallet_create

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

            //创建身份(内部自动保存了钱包)
            val identity = Identity.createIdentity(
                "MyFirstIdentity",
                confirmPassword,
                "PASSWORD_HINT",
                Network.TESTNET,
                Metadata.HD
            )
            //导出助记词
            val mnemonicCode = identity.exportIdentity(confirmPassword)
            info { "助记词==$mnemonicCode" }
           val mnemonicCodes= splitMnemonic(mnemonicCode)
            //发送数据
            LiveDataBus.getChannel(KEY_MNEMONIC_CODE).value = mnemonicCodes
            LiveDataBus.getChannel(KEY_IDENTITY).value = identity
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
