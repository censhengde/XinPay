package com.ringle.xinpay.wallet.ui


import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.wallet.ui.cloud.CloudWalletFragment
import com.ringle.xinpay.wallet.ui.hd.HDWalletDefaultFragment
import com.ringle.xinpay.wallet.ui.hd.HDWalletMainFragment
import com.tencent.mmkv.MMKV
import kotlinx.android.synthetic.main.fragment_wallet_main.*
import org.consenlabs.tokencore.wallet.Identity
import org.jetbrains.anko.info
import kotlin.reflect.KClass

/**
 * 是wallet模块一切页面的容器
 *这里决定ViewPager显示的页面
 * 实现条件导航
 */
open class WalletMainFragment : BaseFragment() {

    private var isHDWalletCreated = false
    private var isCloudWalletLogined = false
    override fun setContentView(): Int = R.layout.fragment_wallet_main
    protected open val mPages: ArrayList<Fragment> = ArrayList(3)
    private lateinit var mViewPagerAdapter: VPageAdapter
    protected open val mTabTitles by lazy(LazyThreadSafetyMode.NONE) {
        arrayListOf(
            "首页",
            "云钱包",
            "HD钱包"
        )
    }


    override fun initData() {
        info { "initData执行" }
        initCloudWalletMainPage()
        initHDWalletMainPage()
        initTab()
//        WalletMainFragment::class.java
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
        isHDWalletCreated = Identity.currentIdentity != null
        when (isHDWalletCreated) {
            true -> mPages.add(2, HDWalletMainFragment())
            false -> mPages.add(2, HDWalletDefaultFragment())
        }
    }

    private fun initTab() {
        mViewPagerAdapter = VPageAdapter(childFragmentManager, 0, mPages, mTabTitles)
        vp_wallet.adapter = mViewPagerAdapter
        tl_wallet.setupWithViewPager(vp_wallet)

    }


    override fun onResume() {
        super.onResume()
        freshCloudWalletMainPageState()
        freshHDWalletMainPageState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mPages.clear()
    }

    /**
     *检查HDWalletMainPageState,
     */
    private fun freshHDWalletMainPageState() {
        //true:说明已有身份
        val currentState = Identity.currentIdentity != null
        if (currentState != isHDWalletCreated) {
            //更新状态
            isHDWalletCreated = currentState
            mViewPagerAdapter.replace(2,HDWalletMainFragment())
        }

    }

    private fun freshCloudWalletMainPageState() {}


    /**
     *适配器
     */
    private class VPageAdapter(
        val fm: FragmentManager,
        behavior: Int,
        val pages: MutableList<Fragment>,
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

        /**
         *替换指定位置上的Fragment
         */
        fun replace(position: Int, newFragment: Fragment) {
            val transation = fm.beginTransaction()
            transation.remove(pages[position])
            pages.removeAt(position)
            pages.add(position, newFragment)
            transation.commit()
            notifyDataSetChanged()
        }


        override fun getItemPosition(`object`: Any): Int {
            if (`object` is HDWalletDefaultFragment) return POSITION_NONE
            return POSITION_UNCHANGED
        }
    }


}
