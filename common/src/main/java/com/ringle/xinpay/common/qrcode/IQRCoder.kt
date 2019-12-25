package com.ringle.xinpay.common.qrcode

import android.graphics.Bitmap
import android.graphics.Color

/**
 * create by 岑胜德
 * on 2019/12/18
 * 说明:面向应用层统一api
 *
 */
interface IQRCoder {

    /**
     *生成二维码
     */
    fun encodeQRCode(
        content: String,//二维码内容
        size: Int = 750,//宽高，单位px，默认750
        foregroundColor: Int = Color.BLACK,//前景色，默认黑色
        backgroundColor: Int = Color.WHITE,//背景色，默认白色
        logoResId: Int=0//logo资源id
        , callback: (Bitmap) -> Unit//回调函数
    )
}