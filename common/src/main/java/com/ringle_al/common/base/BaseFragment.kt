package com.ringle_al.common.base

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

abstract class BaseFragment : NavHostFragment(), AnkoLogger {
    abstract fun setContentView(): Int
    protected open fun setActionBarView(layoutId: Int) {
        if (layoutId != 0) {
            activity?.actionBar?.let {
                it.setDisplayShowHomeEnabled(false)
                it.setDisplayShowCustomEnabled(true)
                val view = LayoutInflater.from(activity).inflate(layoutId, null, false)
                val lp = ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
                it.setCustomView(view,lp)
            }
        }

    }

    private lateinit var mNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val contentView = inflater.inflate(setContentView(), container, false)

        mNavController = findNavController(this)
        info { "" }

        return contentView
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}