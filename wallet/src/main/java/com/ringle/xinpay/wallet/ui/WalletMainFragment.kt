package com.ringle.xinpay.wallet.ui


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.wallet.ui.cloud.CloudWalletFragment
import com.ringle.xinpay.wallet.ui.hd.HDWalletDefaultFragment
import com.ringle.xinpay.wallet.ui.hd.HDWalletMainFragment
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.fragment_wallet_main.*
import org.consenlabs.tokencore.wallet.Identity

/**
 * 是wallet模块一切页面的容器
 *这里决定ViewPager显示的页面
 * 实现条件导航
 */
open class WalletMainFragment : BaseFragment() {

    private var isHDWalletLogined = false
    private var isCloudWalletLogined = false
    override fun setContentView(): Int = R.layout.fragment_wallet_main
    protected open val mPages: ArrayList<Fragment> = ArrayList(3)

    protected open val mTabTitles by lazy(LazyThreadSafetyMode.NONE) {
        arrayListOf(
            "首页",
            "云钱包",
            "HD钱包"
        )
    }


    override fun initData() {
        initCloudWalletMainPage()
        initHDWalletMainPage()
        initTab()
    }

    /**
     *初始化CloudWalletMainPage
     */
    private fun initCloudWalletMainPage() {
        isCloudWalletLogined = MMKV.defaultMMKV().decodeBool("isCloudWalletLogined")
        //根据登录状态初始化页面
        mPages.add(0, HDWalletHomeFragment())
        when (isCloudWalletLogined) {
            true -> mPages.add(1, CloudWalletFragment())
            false -> mPages.add(1, CloudWalletFragment())
        }
    }

    /**
     *初始化HDWalletMainPage
     */
    private fun initHDWalletMainPage() {
        //判断是否存在currentIdentity
        isHDWalletLogined = Identity.currentIdentity != null
        when (isHDWalletLogined) {
            true -> mPages.add(2, HDWalletMainFragment())
            false -> mPages.add(2,HDWalletMainFragment())
        }
    }

    private fun initTab() {
        vp_wallet.adapter = VPageAdapter(childFragmentManager, 0, mPages, mTabTitles)
        tl_wallet.setupWithViewPager(vp_wallet)

    }


    override fun onResume() {
        super.onResume()
        freshCloudWalletMainPageState()
//        freshHDWalletMainPageState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPages.clear()
    }

    /**
     *检查HDWalletMainPageState,
     */
    private fun freshHDWalletMainPageState() {
        //判断页面内容是否发生改变
        val currentState = Identity.currentIdentity != null
        if (currentState != isHDWalletLogined) {
            isHDWalletLogined = currentState
            vp_wallet.removeViewAt(2)
            when (isHDWalletLogined) {
                true -> mPages.add(2, HDWalletMainFragment())
                false -> mPages.add(2, HDWalletDefaultFragment())
            }
            //通知数据源以改变
            vp_wallet.adapter?.notifyDataSetChanged()
        }
    }

    private fun freshCloudWalletMainPageState() {}


    /**
     *适配器
     */
    private class VPageAdapter(
        fm: FragmentManager,
        behavior: Int,
        val pages: List<Fragment>,
        val titles: List<String>
    ) : FragmentStatePagerAdapter(fm, behavior) {

        override fun getItem(position: Int): Fragment {
            return pages[position]
        }

        override fun getCount(): Int {
            Log.e("pages.size", "${pages.size}")
            return pages.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }


    }


}
