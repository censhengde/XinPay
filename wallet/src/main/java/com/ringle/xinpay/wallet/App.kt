package com.ringle.xinpay.wallet

import android.app.Application
import com.ringle.xinpay.common.qrcode.ActualQRCoder
import com.ringle.xinpay.common.qrcode.QRCodeHelper
import com.tencent.mmkv.MMKV

/**
 * create by 岑胜德
 * on 2019/12/13
 * 说明:
 *
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化框架
        QRCodeHelper.init(ActualQRCoder(this))
        MMKV.initialize(this)
    }
}