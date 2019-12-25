package com.ringle.xinpay.wallet.ui


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.fragment_tab_wallet.*
import org.jetbrains.anko.info

/**
 * A simple [Fragment] subclass.
 */
open class WalletFragment : BaseFragment() {

    private var isHDWalletLogined = false
    private var isCloudWalletLogined = false

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
        isCloudWalletLogined = MMKV.defaultMMKV().decodeBool("isCloudWalletLogined")
        isHDWalletLogined = MMKV.defaultMMKV().decodeBool("isHDWalletLogined")
        //根据登录状态初始化页面
        when (isCloudWalletLogined) {
            true -> mPages.add(0, CloudWalletFragment())
            false -> mPages.add(0, CloudWalletFragment())
        }
        when (isHDWalletLogined) {
            true -> mPages.add(1, HDWalletMainFragment())
            false -> mPages.add(1, HDWalletFragment())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTab()
    }


    private fun initTab() {

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
