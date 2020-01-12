package com.ringle.xinpay.wallet.ui.common

import com.ringle.xinpay.wallet.bean.MnemonicCode
import org.jetbrains.anko.info
import kotlin.math.pow

/**
 * create by 岑胜德
 * on 2020/1/2
 * 说明:
 *
 */
fun splitMnemonic(mnemonic: String): List<MnemonicCode> {
    val mnemonicCodes = ArrayList<MnemonicCode>()
    val words = mnemonic.split(" ")
    for (i: Int in 1..words.size) {
        mnemonicCodes.add(MnemonicCode(i, words[i - 1]))
    }
    return mnemonicCodes
}

/**
 *比特币转换为人民币
 */
fun btcToCny(btc: Double, rate: Double): Double {
    return btc * rate
}
/**
 *比特币转聪
 */
fun btcToSatoshi(btc: Double):Long= (btc*(10.0.pow(7))).toLong()

/**
 *聪转比特币
 */
fun satoshiToBtc(satoshi:Long):Double=satoshi/(10.0.pow(7))