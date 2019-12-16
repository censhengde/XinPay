package com.ringle.wallet.ui


import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.View

import com.ringle.wallet.R
import com.ringle_al.common.base.BaseFragment
import com.ringle_al.common.base.navigate
import com.ringle_al.common.base.popBackStack
import com.ringle_al.common.util.LiveDataBus
import kotlinx.android.synthetic.main.fragment_create_wallet.*
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
        initData()
    }

    private fun initData() {
        btn_create_wallet.setOnClickListener {
            val password = et_input_password.text.toString()
            val confirmPassword = et_confirm_password.text.toString()
            if (!checkPassword(password, confirmPassword)) {
                return@setOnClickListener
            }
            val identity = Identity.createIdentity(
                "MyFirstIdentity",
                confirmPassword,
                "PASSWORD_HINT",
                Network.TESTNET,
                Metadata.HD
            )
            val mnemonicWord = identity.exportIdentity(confirmPassword)
            info { "助记词==$mnemonicWord" }
            //将助记词发送出去
            LiveDataBus.getChannel("mne").value=mnemonicWord
            //跳转页面
            navigate(R.id.action_hd_wallet_create_to_hd_wallet_backup)


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
