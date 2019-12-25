package com.ringle.xinpay.wallet.ui


import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.adapter.VerifyMnemonocCodeAdapter
import com.ringle.xinpay.wallet.bean.MnemonicCode
import kotlinx.android.synthetic.main.fragment_verify_mnemonic.*
import org.jetbrains.anko.support.v4.toast
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 *验证助记词
 */
@Suppress("UNCHECKED_CAST")
class VerifyMnemonicCodeFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_verify_mnemonic

    private val mRandomMnemonicCodes = ArrayList<MnemonicCode>()
    private lateinit var mMnemonicCodes: ArrayList<MnemonicCode>
    //被选中的助记词
    private val mSelectedMnemonicCodes = ArrayList<MnemonicCode>(3)
    private val mLabels = ArrayList<Int>()
    private var mOrder = 0

    /**
     *初始化数据
     */
    override fun initData() {

    }

    override fun onStart() {
        super.onStart()
        //拿到助记词
        LiveDataBus.getChannel("mnemonicCode").observe(this, Observer {
            mMnemonicCodes = it as ArrayList<MnemonicCode>
            if (mRandomMnemonicCodes.isEmpty())
            mRandomMnemonicCodes.addAll(mMnemonicCodes)
            //打乱助记词位置
            changeMnemonicCode()
        })
        changeLabels()
    }

    /**
     *随机打乱助记词位置
     */
    private fun changeMnemonicCode() {
            val ra=Random()
        repeat(12) {
           val random= ra.nextInt(12)
            val tem=mRandomMnemonicCodes[it]
            mRandomMnemonicCodes[it]=mRandomMnemonicCodes[random]
            mRandomMnemonicCodes[random]=tem
        }
    }

    override fun onResume() {
        super.onResume()
        rv_mnemonic_code_list.layoutManager = GridLayoutManager(activity!!, 4)
        val adapter = VerifyMnemonocCodeAdapter(R.layout.item_mnemonic_list, mRandomMnemonicCodes)
        //条目点击
        adapter.setOnItemClickListener { _ad, view, position ->
            val code = mRandomMnemonicCodes[position]
            //依次填充助记词
            when (mOrder) {
                0 -> {
                    tv_checked_code_01.text = code.code
                    mSelectedMnemonicCodes.add(0, code)
                    mOrder++
                }
                1 -> {
                    tv_checked_code_02.text = code.code
                    mSelectedMnemonicCodes.add(1, code)
                    mOrder++
                }
                2 -> {
                    tv_checked_code_03.text = code.code
                    mSelectedMnemonicCodes.add(2, code)
                    mOrder++
                }

            }
        }
        rv_mnemonic_code_list.adapter = adapter
        onClickConfirm()
    }

    /**
     *改变label
     */
    @SuppressLint("SetTextI18n")
    private fun changeLabels() {
        //清零
        if (mLabels.isNotEmpty()) {
            mLabels.clear()
        }
        val ra = Random()
        val set = HashSet<Int>()
        //从12个数中取三个不重复随机数
        while (set.size < 3) {
            val r = ra.nextInt(11) + 1
            //注意:当add到相同元素时,size不会改变
            set.add(r)
        }
        mLabels.addAll(set)

        tv_label_01.text = "第${mLabels[0]}位"
        tv_label_02.text = "第${mLabels[1]}位"
        tv_label_03.text = "第${mLabels[2]}位"

    }

    /**
     *点击确定
     */
    private fun onClickConfirm() {
        btn_confirm.setOnClickListener {
            //比对
            val isPass = mLabels[0] == mSelectedMnemonicCodes[0].index
                    && mLabels[1] == mSelectedMnemonicCodes[1].index
                    && mLabels[2] == mSelectedMnemonicCodes[2].index
            //不通过
            if (!isPass) {
                toast("验证未通过")
                return@setOnClickListener
            }

            //跳转
            toast("验证通过")
        }
    }

}
