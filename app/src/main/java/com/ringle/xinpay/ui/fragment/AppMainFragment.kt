package com.ringle.xinpay.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ringle.xinpay.ui.fragment.tab.DealFragment
import com.ringle.xinpay.ui.fragment.tab.HomeFragment
import com.ringle.xinpay.ui.fragment.tab.TabWalletFragment

import com.ringle.xinpay.app.R
import kotlinx.android.synthetic.main.fragment_app_main.*

/**
 * A simple [Fragment] subclass.
 */
class AppMainFragment : Fragment(),
    ViewPager.OnPageChangeListener,
    BottomNavigationView.OnNavigationItemSelectedListener {


    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        bnv_app_main.menu.getItem(position).isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                vp_app_main.currentItem = 0
                return true
            }
            R.id.deal -> {
                vp_app_main.currentItem = 1
                return true
            }
            R.id.wallet -> {
                vp_app_main.currentItem = 2
                return true
            }

        }
        return false
    }

   //这里加载的是三个空白容器fragment
    private val mPages = listOf(HomeFragment(), DealFragment(), TabWalletFragment())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bnv_app_main.setOnNavigationItemSelectedListener(this)
        //禁用默认着色
        bnv_app_main.itemIconTintList = null
        vp_app_main.addOnPageChangeListener(this)
        vp_app_main.adapter = VPageAdapter(childFragmentManager, 0, mPages)
    }


    /**
     *适配器
     */
    private class VPageAdapter(
        fm: FragmentManager,
        behavior: Int,
        val pages: List<Fragment>

    ) :
        FragmentStatePagerAdapter(fm, behavior) {


        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            return pages.size
        }


        override fun getPageTitle(position: Int): CharSequence? {

            return null
        }
    }
}
