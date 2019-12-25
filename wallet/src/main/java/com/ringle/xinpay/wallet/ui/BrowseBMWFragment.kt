package com.ringle.xinpay.wallet.ui

import com.ringle.wallet.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.base.navigate
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.adapter.MnemonicListAdapter
import com.ringle.xinpay.wallet.bean.ItemMnemonicWord
import kotlinx.android.synthetic.main.fragment_browse_bmw.*
import kotlinx.android.synthetic.main.fragment_browse_bmw.rv_mnemonic_word_list
import org.jetbrains.anko.info
import kotlin.collections.ArrayList

/**
 * create by 岑胜德
 * on 2019/12/16
 * 说明:
 */
class BrowseBMWFragment : BaseFragment() {
    override fun setContentView(): Int {

        return R.layout.fragment_browse_bmw
    }


    private val mMnemonicWords = ArrayList<ItemMnemonicWord>()



    override fun onStart() {
        super.onStart()
        //拿到助记词
        LiveDataBus.getChannel("mne").observe(this, Observer {
            val words = (it as String).split(" ")
            info { "words.size==${words.size}" }
            for (i: Int in 1..words.size) {
                mMnemonicWords.add(ItemMnemonicWord(i, words[i - 1]))
            }
        })

    }


    override fun initData() {
        rv_mnemonic_word_list.layoutManager = GridLayoutManager(activity, 4)
        val adapter = MnemonicListAdapter(
            context = activity,
            layoutId = R.layout.item_mnemonic_list,
            datas = mMnemonicWords
        )
        rv_mnemonic_word_list.adapter = adapter
        //跳转页面
        btn_next.setOnClickListener {
            navigate(R.id.action_browse_to_verify)
        }
    }



}
