package com.ringle.xinpay.wallet.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ringle.xinpay.wallet.bean.ItemCoinList

/**
 * create by 岑胜德
 * on 2019/12/19
 * 说明:
 *
 */
class CoinListAdapter(layoutId: Int, datas: MutableList<ItemCoinList>?) :
    BaseQuickAdapter<ItemCoinList,BaseViewHolder>( layoutId, datas) {



    override fun convert(helper: BaseViewHolder, item: ItemCoinList?) {

    }
}