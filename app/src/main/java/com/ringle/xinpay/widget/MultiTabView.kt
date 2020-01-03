package com.ringle.xinpay.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ringle.xinpay.app.R
import kotlinx.android.synthetic.main.multi_tab_view.view.*

/**
 * create by 岑胜德
 * on 2019/12/11
 * 说明:
 *
 */
class MultiTabView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {
   private lateinit var mTabs:List<String>
    val TOP = 0
    val BOTTOM = 1
    private val mTabOn: Int

    init {
        orientation = VERTICAL
        LayoutInflater.from(context).inflate(R.layout.multi_tab_view, this, true)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MultiTabView)
        mTabOn = typedArray.getInt(R.styleable.MultiTabView_tabOn, TOP)

        typedArray.recycle()
    }

    /**
     *初始化TabLayout
     */
    init {
        when (mTabOn) {
            TOP -> {

            }
            BOTTOM -> {
                (tl.layoutParams as LayoutParams).gravity = bottom
            }
        }


    }

    fun setTab( tabs:List<String>){
        this.mTabs=tabs
    }

    private class MyFragmentPagerAdapter(fm: FragmentManager, behavior: Int,val pageList:List<Fragment>):
        FragmentPagerAdapter(fm, behavior) {
        override fun getItem(position: Int): Fragment =pageList[position]
        override fun getCount(): Int =pageList.size

    }
}