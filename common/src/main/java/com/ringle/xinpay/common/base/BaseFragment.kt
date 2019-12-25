package com.ringle.xinpay.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ringle.xinpay.common.util.LiveDataBus
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import androidx.lifecycle.Observer

fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

fun Fragment.navigate(actionId: Int, bundle: Bundle? = null) {
    if (bundle == null) {
        LiveDataBus.getChannel("hdWalletController").observe(this, Observer { t ->
            (t as NavController).navigate(actionId)
        })
    } else {
        LiveDataBus.getChannel("hdWalletController").observe(this, Observer { t ->
            (t as NavController).navigate(actionId, bundle)
        })
    }
}

fun Fragment.popBackStack() {
    LiveDataBus.getChannel("hdWalletController").observe(this, Observer { t ->
        (t as NavController).popBackStack()
    })
}

fun Fragment.popBackStack(destinationId: Int, inclusive: Boolean) {
    LiveDataBus.getChannel("hdWalletController").observe(this, Observer { t ->
        (t as NavController).popBackStack(destinationId, inclusive)
    })
}

abstract class BaseFragment : NavHostFragment(), AnkoLogger {
    abstract fun setContentView(): Int


    protected lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val contentView = inflater.inflate(setContentView(), container, false)

        mNavController = findNavController()
        info { "" }

        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }


    protected open fun initData() {}
}