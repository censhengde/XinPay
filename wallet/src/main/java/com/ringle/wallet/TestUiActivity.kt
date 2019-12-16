package com.ringle.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.ringle.wallet.adapter.MnemonicListAdapter
import kotlinx.android.synthetic.main.fragment_browse_bmw.*

class TestUiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_browse_bmw)
        val mnemonicList = ArrayList<String>(12)
        for (i: Int in 0..12) {
            mnemonicList.add("助记词$i")
        }
        rv_mnemonic_word_list.layoutManager = GridLayoutManager(this, 4)
        val adapter = MnemonicListAdapter(this, R.layout.item_mnemonic_list, mnemonicList)
        rv_mnemonic_word_list.adapter = adapter
    }
//private val mnemonicListAdapter=
}
