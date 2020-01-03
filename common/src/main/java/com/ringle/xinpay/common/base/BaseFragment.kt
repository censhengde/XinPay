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
import com.ringle.xinpay.R
import kotlinx.android.synthetic.main.common_action_bar.*


abstract class BaseFragment : NavHostFragment(), AnkoLogger {
    abstract fun setContentView(): Int


    protected lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val contentView = inflater.inflate(setContentView(), container, false)

//        mNavController = findNavController()
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


    fun initActionBar(
        left: Int = R.drawable.resources_img_nav_navback,
        title: String,
        titleLogo: Int? = null,
        right: Int? = null,
        onClickBack: (() -> Unit)? = null,
        onClickTitle: (() -> Unit)? = null,
        background: Int? = null
    ) {
        //背景色
        background?.let {
            common_bar.setBackgroundColor(background)
        }
        //返回键
        iv_common_action_bar_left.setImageResource(left)
        iv_common_action_bar_left.setOnClickListener {
            onClickBack?.invoke()
        }
        //标题
        tv_common_action_bar_center_title.text = title
        tv_common_action_bar_center_title.setOnClickListener {
            onClickTitle?.invoke()
        }
        titleLogo?.let {
            val drawable = resources.getDrawable(titleLogo, null)
            tv_common_action_bar_center_title.setCompoundDrawables(null, null, drawable, null)
//            tv_common_action_bar_center_title.compoundDrawablePadding=5
        }
        //右图标
        right?.let {
            iv_common_action_bar_right.setImageResource(right)
        }
    }
}