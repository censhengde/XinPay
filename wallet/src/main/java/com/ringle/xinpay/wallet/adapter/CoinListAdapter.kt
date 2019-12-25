package com.ringle.xinpay.wallet.adapter

import android.content.Context
import com.ringle.xinpay.wallet.bean.ItemCoinList
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder

/**
 * create by 岑胜德
 * on 2019/12/19
 * 说明:
 *
 */
class CoinListAdapter(context: Context?, layoutId: Int, datas: MutableList<ItemCoinList>?) :
    CommonAdapter<ItemCoinList>(context, layoutId, datas) {



    override fun convert(holder: ViewHolder?, item: ItemCoinList?, position: Int) {

    }
}