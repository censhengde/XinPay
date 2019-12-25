package com.ringle.xinpay.wallet.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.qrcode.QRCodeHelper
import kotlinx.android.synthetic.main.fragment_receive_qrcode.*


class QRCodeReceiveFragment : BaseFragment() {
    override fun setContentView(): Int {
        return R.layout.fragment_receive_qrcode
    }

    override fun initData() {
        initActionBar(R.drawable.md_nav_back, title = "BTC")

    }
    private fun initActionBar(lResId: Int = 0, rResId: Int = 0, title: String = "") {
        if (lResId != 0) activity?.findViewById<ImageView>(R.id.iv_common_action_bar_left)?.setImageResource(
            lResId
        )
        if (title != "") activity?.findViewById<TextView>(R.id.tv_common_action_bar_center_title)
            ?.text = title
        if (rResId != 0) activity?.findViewById<ImageView>(R.id.iv_common_action_bar_right)?.setImageResource(
            rResId
        )
    }

    private fun initQRCode(adress:String,logo:Int){
        QRCodeHelper.encodeQRCode(adress,logoResId = logo,callback={
            iv_qr_code.setImageBitmap(it)
        })
    }





}
