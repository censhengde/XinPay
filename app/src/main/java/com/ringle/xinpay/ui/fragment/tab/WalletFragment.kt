package com.ringle.xinpay.ui.fragment.tab


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.ringle.xinpay.ui.fragment.wallet.CloudWalletFragment
import com.ringle.xinpay.ui.fragment.wallet.HDWalletFragment
import com.ringle.xinpay.ui.fragment.wallet.MultiSigWalletFragment
import com.ringle_al.common.base.BaseFragment

import com.ringle_al.wallet.R
import kotlinx.android.synthetic.main.fragment_tab_wallet.*
import org.jetbrains.anko.info

/**
 * A simple [Fragment] subclass.
 */
open class WalletFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_tab_wallet

    protected open val mPages: ArrayList<Fragment> =
        arrayListOf(CloudWalletFragment(),HDWalletFragment(),MultiSigWalletFragment())

    protected open val mTabTitles by lazy(LazyThreadSafetyMode.NONE) { arrayListOf("云钱包", "HD钱包", "多签钱包") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        info { "onCreate执行" }
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        initTab()
//    }

    override fun onStart() {
        super.onStart()
        info { "onStart 执行" }
        initTab()
    }

//    override fun onStop() {
//        super.onStop()
//        info { "onStop 执行" }
//        mPages.clear()
//    }
    private fun initTab() {
        vp_wallet.adapter = VPageAdapter(
            this.childFragmentManager,
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
