package com.ringle_al.common.util

import android.os.Looper

/**
 *create by 岑胜德
 *on 2019/10/31
 *说明:
 *
 */

fun isMainThread():Boolean=Looper.getMainLooper()== Looper.myLooper()