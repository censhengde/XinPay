package com.ringle.xinpay.wallet.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.ringle.wallet.R
import com.ringle.xinpay.wallet.bean.ItemMnemonicWord
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * create by 岑胜德
 * on 2019/12/16
 * 说明:
 *
 */
open class MnemonicListAdapter(context: Context?, layoutId: Int, datas: MutableList<ItemMnemonicWord>?) :
    CommonAdapter<ItemMnemonicWord>(context, layoutId, datas) {

    @SuppressLint("SetTextI18n")
    override fun convert(holder: ViewHolder?, t: ItemMnemonicWord?, position: Int) {

     val textView=   holder!!.getView<TextView>(R.id.tv_mnemonic_word)
        textView.text = "${t?.index}-${t?.word}"

    }
}