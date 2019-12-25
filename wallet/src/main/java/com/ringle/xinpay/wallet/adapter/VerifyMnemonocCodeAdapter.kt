package com.ringle.xinpay.wallet.adapter

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ringle.wallet.R
import com.ringle.xinpay.wallet.bean.MnemonicCode

/**
 * create by 岑胜德
 * on 2019/12/25
 * 说明:
 *
 */
class VerifyMnemonocCodeAdapter(layoutResId: Int, data: MutableList<MnemonicCode>?) :
    BaseQuickAdapter<MnemonicCode, BaseViewHolder>(layoutResId, data) {


    override fun convert(helper: BaseViewHolder, item: MnemonicCode?) {
        val textView = helper.getView<TextView>(R.id.tv_mnemonic_word)
        textView.text = item?.code
    }
}