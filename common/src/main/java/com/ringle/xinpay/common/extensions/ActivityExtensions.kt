package com.ringle.xinpay.common.extensions

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import com.ringle.xinpay.R

/**
 * create by 岑胜德
 * on 2019/12/18
 * 说明:
 *
 */
fun Activity.initActionBar(lResId: Int, rResId: Int, title: String) {
    findViewById<ImageView>(R.id.iv_common_action_bar_left).setImageResource(lResId)
    findViewById<TextView>(R.id.tv_common_action_bar_center_title).text = title
    findViewById<ImageView>(R.id.iv_common_action_bar_right).setImageResource(rResId)
}