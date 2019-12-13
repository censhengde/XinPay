package com.ringle.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ringle_al.common.util.LiveDataBus
import org.consenlabs.tokencore.wallet.KeystoreStorage
import org.consenlabs.tokencore.wallet.WalletManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.wallpaperManager
import java.io.File

class MainActivity : AppCompatActivity(), AnkoLogger, KeystoreStorage {
    /**
     *加载秘钥文件
     */
    override fun getKeystoreDir(): File {
        return File("")
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
        mMainNavControoler = Navigation.findNavController(this, R.id.fragment_container)
        LiveDataBus.getChannel("hdWalletController").value = mMainNavControoler
    }

    override fun onSupportNavigateUp(): Boolean {

        return mMainNavControoler.navigateUp()
    }
}
