package com.ringle.xinpay.wallet

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.ringle.wallet.R
import com.ringle.xinpay.common.qrcode.QRCodeHelper
import kotlinx.android.synthetic.main.fragment_receive_qrcode.*

class TestUiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_base_deal)
//        initActionBar()

//        initQRCode()
    }

    @SuppressLint("SetTextI18n")
    private fun initActionBar(){
        findViewById<ImageView>(R.id.iv_common_action_bar_left).setImageResource(R.drawable.md_nav_back)
        findViewById<TextView>(R.id.tv_common_action_bar_center_title).text = "BTC收款"
        findViewById<ImageView>(R.id.iv_common_action_bar_right).setImageResource(R.drawable.resources_img_nav_nav_menu_set)
    }
    private fun initQRCode(){
        QRCodeHelper.encodeQRCode("收款地址",logoResId = R.drawable.resources_img_investment_btc,callback={
        iv_qr_code.setImageBitmap(it)
        })
    }
}
