package com.ringle.xinpay.common.widgets

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.util.AttributeSet
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView

/**
 * create by 岑胜德
 * on 2020/1/6
 * 说明:点击复制文本内容TextView封装
 *
 */
class CopyTextView(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {

    private val mClipboardManager by lazy {
        context?.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
    }

    private lateinit var mClipData: ClipData


    /**
     *复制内容
     */
    fun copyContent() {
        mClipData = ClipData.newPlainText("text", text.toString())
        mClipboardManager.setPrimaryClip(mClipData)
        Toast.makeText(context, "已复制内容", Toast.LENGTH_SHORT).show()
    }
}