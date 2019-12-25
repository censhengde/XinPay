package com.ringle.xinpay.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ringle.base.getFilePath
import com.ringle.wallet.R
import com.ringle.xinpay.common.util.LiveDataBus
import org.consenlabs.tokencore.wallet.KeystoreStorage
import org.consenlabs.tokencore.wallet.WalletManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.File

class MainActivity : AppCompatActivity(), AnkoLogger, KeystoreStorage {
    /**
     *加载秘钥文件
     */
//    private val sdcardRoot= getExternalFilesDir("xinpay")?.absolutePath;
    override fun getKeystoreDir(): File {
        val path=getFilePath("xinpay")
        info { "sdcardRoot==$path" }
        return File(path)
    }

    private lateinit var mMainNavControoler: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WalletManager.storage = this
        WalletManager.scanWallets()
    }

    override fun onResume() {
        super.onResume()
        mMainNavControoler = Navigation.findNavController(this,
            R.id.fragment_container
        )
        LiveDataBus.getChannel("hdWalletController").value = mMainNavControoler
    }

    override fun onSupportNavigateUp(): Boolean {

        return mMainNavControoler.navigateUp()
    }
}
