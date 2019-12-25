package com.ringle.xinpay.wallet.ui


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.ringle.wallet.R
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.util.LiveDataBus
import com.ringle.xinpay.wallet.adapter.MnemonicListAdapter
import com.ringle.xinpay.wallet.bean.ItemMnemonicWord
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.fragment_verify_mnemonic.*
import org.jetbrains.anko.info
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * A simple [Fragment] subclass.
 */
class VerifyMnemonicFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_verify_mnemonic

    private val mMnemonicWords = ArrayList<ItemMnemonicWord>()
    private val mLabels = ArrayList<Int>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_mnemonic_word_list.layoutManager = GridLayoutManager(activity, 4)
        val adapter = object : MnemonicListAdapter(
            context = activity,
            layoutId = R.layout.item_mnemonic_list,
            datas = mMnemonicWords
        ) {
            //重写convert
            override fun convert(holder: ViewHolder?, t: ItemMnemonicWord?, position: Int) {
                val textView = holder!!.getView<TextView>(R.id.tv_mnemonic_word)
                textView.text = t?.word

            }
        }
        rv_mnemonic_word_list.adapter = adapter


    }



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
        changeLabels()
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
}
