package com.ringle.xinpay.wallet.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.wallet.bean.MnemonicCode

/**
 * create by 岑胜德
 * on 2019/12/16
 * 说明:
 *
 */
open class MnemonicListAdapter(
    data: MutableList<MnemonicCode>?=null,
    layoutResId: Int
) : BaseQuickAdapter<MnemonicCode, BaseViewHolder>(layoutResId, data) {


    @SuppressLint("SetTextI18n")
    override fun convert(helper: BaseViewHolder, item: MnemonicCode?) {
        val textView = helper.getView<TextView>(R.id.tv_mnemonic_word)
        textView.text = "${item?.index}-${item?.code}"
    }


}