package com.ringle.xinpay.wallet.ui.hd.transation


import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.qrcode.QRCodeHelper
import kotlinx.android.synthetic.main.fragment_hdwallet_transation_recieve.*


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
            })

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
