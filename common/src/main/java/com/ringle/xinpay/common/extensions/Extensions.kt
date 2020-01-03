package com.ringle.xinpay.common.extensions

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ringle.xinpay.common.util.LiveDataBus
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import com.ringle.xinpay.common.global.KEY_GLOBAL_NAV_CONTROLLER

/**
 * create by 岑胜德
 * on 2019/12/30
 * 说明:
 *
 */
fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

fun Fragment.navigate(actionId: Int, bundle: Bundle? = null) {
    if (bundle == null) {
        LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).observe(this, Observer { t ->
            (t as NavController).navigate(actionId)
        })
    } else {
        LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).observe(this, Observer { t ->
            (t as NavController).navigate(actionId, bundle)
        })
    }
}

fun Fragment.popBackStack() :Boolean{
    var ret=true
    LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).observe(this, Observer { t ->
        ret= (t as NavController).popBackStack()
    })
    return ret
}

fun Fragment.popBackStack(destinationId: Int, inclusive: Boolean) :Boolean{
    var ret=true
    LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).observe(this, Observer { t ->
      ret=  (t as NavController).popBackStack(destinationId, inclusive)
    })
    return ret
}

fun Fragment.navigate( deepLink:String, navOptions: NavOptions? = null) {
    val uri=Uri.parse(deepLink)
    val intent= Intent()
    intent.data = uri
    if (navOptions == null) {
        LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).observe(this, Observer { t ->
            (t as NavController).handleDeepLink(intent)
        })
    } else {
        LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).observe(this, Observer { t ->
            (t as NavController).handleDeepLink(intent)
        })
    }
}