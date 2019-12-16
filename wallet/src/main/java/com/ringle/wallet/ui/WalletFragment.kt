package com.ringle.wallet.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ringle_al.common.base.BaseFragment

import com.ringle.wallet.R
import com.ringle_al.common.util.LiveDataBus
import kotlinx.android.synthetic.main.fragment_tab_wallet.*
import org.jetbrains.anko.info

/**
 * A simple [Fragment] subclass.
 */
open class WalletFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_tab_wallet
    protected open val mPages: ArrayList<Fragment> = ArrayList()

    protected open val mTabTitles by lazy(LazyThreadSafetyMode.NONE) {
        arrayListOf(
            "云钱包",
            "HD钱包"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info { "onCreate执行" }
        mPages.add(CloudWalletFragment())
        mPages.add(HDWalletFragment())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTab()
    }




    private fun initTab() {
//        val cloudWalletFragment = NavHostFragment.create(R.navigation.nav_graph_cloud_wallet)
//        val hdWalletFragment = NavHostFragment.create(R.navigation.nav_graph_hd_wallet_create)


        vp_wallet.adapter = VPageAdapter(
            childFragmentManager,
            0,
            mPages,
            mTabTitles
        )
        tl_wallet.setupWithViewPager(vp_wallet)

    }


    /**
     *适配器
     */
    private class VPageAdapter(
        fm: FragmentManager,
        behavior: Int,
        val pages: List<Fragment>,
        val titles: List<String>
    ) :
        FragmentStatePagerAdapter(fm, behavior) {


        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            return pages.size
        }


        override fun getPageTitle(position: Int): CharSequence? {

            return titles[position]
        }
    }
}
