package com.ringle.xinpay.wallet.coin

/**
 * create by 岑胜德
 * on 2019/12/17
 * 说明:所有币种的抽象
 *
 */
data class Coin(
    //名称
    var name: String = "",
    //余额
    var balance: String? = null,
    //价值人民币
    var cny: String? = null,
    //logo
    val logo: String? = null
)
