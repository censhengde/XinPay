package com.ringle.xinpay.wallet.ui.hd.imp


import android.text.TextUtils
import com.ringle.base.getFilePath
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.extensions.popBackStack
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.wallet.global.KEY_WALLET
import kotlinx.android.synthetic.main.fragment_hdwallet_create.*
import kotlinx.android.synthetic.main.fragment_hdwallet_import.*
import org.consenlabs.tokencore.wallet.Identity
import org.consenlabs.tokencore.wallet.WalletManager
import org.consenlabs.tokencore.wallet.model.BIP44Util
import org.consenlabs.tokencore.wallet.model.ChainType
import org.consenlabs.tokencore.wallet.model.Metadata
import org.consenlabs.tokencore.wallet.model.Network
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast
import java.lang.Exception as JavaLangException

/**
 *导入钱包
 */
class HDWalletImportFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_hdwallet_import
    }


    override fun initData() {
        initActionBar(
            R.drawable.md_nav_back,
            "导入钱包",
            R.drawable.resources_img_nav_nav_scan,
            onClickBack = {
                navigate(R.id.action_back_out_graph_hd_wallet_import)
            })
        //点击开始恢复
        btn_hdwallet_import.setOnClickListener {
            //助记词
            val mnemonic = et_hdwallet_import_mnemonic_code.text.toString()
            info { mnemonic }
            //密码
            val password = et_hdwallet_input_password.text.toString()
            //确认密码
            val confirmPassword = et_hdwallet_confirm_password.text.toString()
            //检查密码
            if (!checkPassword(password, confirmPassword)) {
                return@setOnClickListener
            }
            //从网络恢复身份(会覆盖掉currentIdentity)
           Identity.recoverIdentity(mnemonic,"",password,"",Network.TESTNET,Metadata.HD)
            if(Identity.getCurrentIdentity()==null){
                toast("请先创建 Identity")
                return@setOnClickListener
            }
            //元数据
            val metadata = Metadata()
            metadata.network = Network.TESTNET
            metadata.chainType=ChainType.BITCOIN
            metadata.walletType = Metadata.HD
            val path = BIP44Util.BITCOIN_TESTNET_PATH
            try {

                //耗时操作,有待改进
                val wallet = WalletManager.importWalletFromMnemonic(
                    metadata,
                    mnemonic,
                    path,
                    confirmPassword,
                    true
                )
                //发送数据
                LiveDataBus.getChannel(KEY_WALLET).value = wallet
                //跳转首页
                toast("导入成功!")
                navigate(R.id.action_back_out_graph_hd_wallet_import)
            } catch (e: JavaLangException) {
                e.printStackTrace()
                toast("导入失败,请检查输入:$e")
            }

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
