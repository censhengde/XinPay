package com.ringle.xinpay.common.qrcode

import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper

/**
 * create by 岑胜德
 * on 2019/12/18
 * 说明:二维码API统一封装
 *
 */
object QRCodeHelper :IQRCoder{


    private lateinit var delegate:IQRCoder

    fun init(delegate: IQRCoder){
        this.delegate=delegate
    }
    /**
     *生成二维码图片
     */
    override fun encodeQRCode(
        content: String,
        size: Int,
        foregroundColor: Int,
        backgroundColor: Int,
        logoResId: Int,
        callback:(Bitmap)->Unit) {
       return delegate.encodeQRCode(content,size,foregroundColor, backgroundColor, logoResId,callback)
    }
}