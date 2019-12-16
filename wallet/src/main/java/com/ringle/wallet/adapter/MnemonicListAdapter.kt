package com.ringle.wallet.adapter

import android.content.Context
import android.widget.TextView
import com.ringle.wallet.R
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.item_mnemonic_list.view.*

/**
 * create by 岑胜德
 * on 2019/12/16
 * 说明:
 *
 */
class MnemonicListAdapter(context: Context?, layoutId: Int, datas: MutableList<String>?) :
    CommonAdapter<String>(context, layoutId, datas) {
    override fun convert(holder: ViewHolder?, t: String?, position: Int) {

     val textView=   holder!!.getView<TextView>(R.id.tv_mnemonic_word)
        textView.text = t

    }
}