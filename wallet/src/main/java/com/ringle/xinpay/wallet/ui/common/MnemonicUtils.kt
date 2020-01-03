package com.ringle.xinpay.wallet.ui.common

import com.ringle.xinpay.wallet.bean.MnemonicCode
import org.jetbrains.anko.info

/**
 * create by 岑胜德
 * on 2020/1/2
 * 说明:
 *
 */
fun splitMnemonic(mnemonic:String):List<MnemonicCode>{
    val mnemonicCodes = ArrayList<MnemonicCode>()
    val words = mnemonic.split(" ")
    for (i: Int in 1..words.size) {
        mnemonicCodes.add(MnemonicCode(i, words[i - 1]))
    }
    return mnemonicCodes
}