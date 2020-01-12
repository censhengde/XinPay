package com.ringle.xinpay.wallet.ui.hd.transation


import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.extensions.navigateUp
import com.ringle.xinpay.common.qrcode.QRCodeHelper
import com.ringle.xinpay.wallet.global.INDEX_BTC_WALLET
import kotlinx.android.synthetic.main.fragment_hdwallet_transation_recieve.*
import org.consenlabs.tokencore.wallet.Identity

/**
 *扫码收款页面
 */
class HDWalletReceiveFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_hdwallet_transation_recieve
    }

    override fun initData() {
        initActionBar(
            title = "BTC",
            titleLogo = R.drawable.resources_img_icon_arrow_down,
            //标题点击事件
            onClickTitle = {
                navigate(R.id.action_frag_hdwallet_transation_recieve_to_frag_hdwallet_transation_choose_coin)
            },
            onClickBack = {
                //navigate(R.id.action_frag_hdwallet_transation_recieve_popupto_frag_wallet_main)
                navigateUp()
            })
        //
        if (Identity.currentIdentity != null) {
            val address = Identity.currentIdentity.wallets[INDEX_BTC_WALLET].address
            initQRCode(address, R.drawable.resources_img_investment_btc)
            tv_hdwallet_transation_receive_address.text = address
            //复制地址
            rl_hdwallet_transation_receive_address.setOnClickListener {
                tv_hdwallet_transation_receive_address.copyContent()
            }
        }
    }


    /**
     *初始化二维码
     */
    private fun initQRCode(adress: String, logo: Int) {
        QRCodeHelper.encodeQRCode(adress, logoResId = logo, callback = {
            iv_qr_code.setImageBitmap(it)
        })
    }


}
