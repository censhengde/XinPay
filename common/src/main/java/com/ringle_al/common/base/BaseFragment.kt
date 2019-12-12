package com.ringle_al.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


fun Fragment.findNavController(): NavController =
    NavHostFragment.findNavController(this)

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
        initToolBar()
    }

    /**
     *ActionBar统一封装
     */
    protected open fun initToolBar() {

    }
}