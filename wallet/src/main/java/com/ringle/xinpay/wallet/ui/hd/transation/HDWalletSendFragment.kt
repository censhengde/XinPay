package com.ringle.xinpay.wallet.ui.hd.transation


import android.annotation.SuppressLint
import com.ringle.xinpay.common.base.BaseFragment
import com.ringle.xinpay.common.extensions.navigate
import com.ringle.xinpay.common.extensions.navigateUp

import com.ringle.xinpay.wallet.R
import com.ringle.xinpay.wallet.global.INDEX_BTC_WALLET
import com.ringle.xinpay.wallet.ui.common.*
import kotlinx.android.synthetic.main.fragment_hdwallet_send.*
import org.bitcoinj.core.NetworkParameters
import org.bitcoinj.params.TestNet3Params
import org.bitcoinj.wallet.Wallet
import org.consenlabs.tokencore.wallet.Identity
import org.consenlabs.tokencore.wallet.model.ChainId
import org.consenlabs.tokencore.wallet.transaction.BitcoinTransaction
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.toast

const val FEE_LONG_TIME = 0.00001
const val FEE_NORMAL = 0.00003
const val FEE_SHORT_TIME = 0.000035

class HDWalletSendFragment : BaseFragment() {
    override fun setContentView(): Int = R.layout.fragment_hdwallet_send
    //余额
    private var mBalance: Double = 0.00000
    //矿工费
    private var mSelectedFee: Double = 0.000
    private var mCny = 0.00000
    //汇率
    private var mExchangeRate: Double = 57815.00
    //这一次交易的全部输入
    private val mInputUTXOs = ArrayList<BitcoinTransaction.UTXO>()

    private val isIdentityExist = Identity.currentIdentity != null

    override fun initData() {
        initActionBar(
            title = "BTC转账",
            titleLogo = R.drawable.resources_img_icon_arrow_down,
            onClickTitle = {
                //跳转更换币种页
                navigate(R.id.action_frag_hdwallet_transation_send_to_frag_hdwallet_transation_choose_coin)
            },
            onClickBack = {
                navigateUp()
            })
//        getBalance()

        initViews()
        onClickConfirm()
    }

    /**
     *获取余额
     */
    private fun getBalance() {
//      val params=  TestNet3Params.get()
//       Wallet(params).currentAddress()
        info { "余额：$mBalance BTC" }
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        tv_hdwallet_transation_send_balance.text = "可用余额:$mBalance BTC"
        rg_hdwallet_transation_send_fee.setOnCheckedChangeListener { group, checkedId ->
            mSelectedFee = when (checkedId) {
                R.id.cb_fee_long_time -> {
                    cb_fee_long_time.isChecked = true
                    cb_fee_normal.isChecked = false
                    cb_fee_short_time.isChecked = false
                    tv_hdwallet_transation_send_fee.text =
                        "旷工费用: $FEE_LONG_TIME BTC ≈ ￥${btcToCny(FEE_LONG_TIME, mExchangeRate)}"
                    FEE_LONG_TIME
                }
                R.id.cb_fee_normal -> {
                    cb_fee_long_time.isChecked = false
                    cb_fee_normal.isChecked = true
                    cb_fee_short_time.isChecked = false
                    tv_hdwallet_transation_send_fee.text =
                        "旷工费用: $FEE_NORMAL BTC ≈ ￥${btcToCny(FEE_NORMAL, mExchangeRate)}"
                    FEE_NORMAL
                }
                R.id.cb_fee_short_time -> {
                    cb_fee_long_time.isChecked = false
                    cb_fee_normal.isChecked = false
                    cb_fee_short_time.isChecked = true
                    tv_hdwallet_transation_send_fee.text =
                        "旷工费用: $FEE_SHORT_TIME BTC ≈ ￥${btcToCny(FEE_SHORT_TIME, mExchangeRate)}"
                    FEE_SHORT_TIME
                }
                else -> throw RuntimeException("checkedId 不存在!")
            }
        }
    }


    /**
     *点击确认
     */
    private fun onClickConfirm() {
        btn_hdwallet_transation_send_confirm.setOnClickListener {
            val address = et_hdwallet_transation_send_address.text.toString()
            val amount = et_hdwallet_transation_send_amount.text.toString().toDouble()
            val memo = et_hdwallet_transation_send_memo.text.toString()
            val fee = ""
//            if (mBalance < amount) {
//                toast("余额不足")
//                return@setOnClickListener
//            }

            val wallet = if (Identity.currentIdentity == null) {
                toast("当前Identity为空!")
                return@setOnClickListener
            } else {
                Identity.currentIdentity.wallets[INDEX_BTC_WALLET]
            }

            //弹框输密码
            showInputDialog(activity!!, confirm = { password ->
                try {

                    if (!wallet.keystore.verifyPassword(password)) {
                        toast("密码错误!")
                        return@showInputDialog
                    }
                    val prvKey = wallet.exportPrivateKey(password)
                    //解锁将用于转账的金额
                    mInputUTXOs.add(
                        BitcoinTransaction.UTXO(
                            "txHash",
                            0,
                            btcToSatoshi(amount + mSelectedFee),
                            wallet.address,
                            wallet.encXPub,
                            null
                        )
                    )
                    //这些参数最终构成这一次交易的全部输出(output utxo)
                    val transaction = BitcoinTransaction(
                        address,
                        0,
                        btcToSatoshi(amount),
                        btcToSatoshi(mSelectedFee),
                        mInputUTXOs
                    )
                    if (memo.isNotEmpty()) {
                        transaction.memo = memo
                    }
                    transaction.signTransaction(
                        ChainId.BITCOIN_TESTNET.toString(),
                        password,
                        wallet
                    )
                    //将交易广播到网络


                    toast("转账成功!")
                } catch (e: Exception) {
                    e.printStackTrace()
                    toast("转账失败!")
                }
            })
        }
    }


}
