package com.ringle.xinpay.wallet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.ringle.base.getFilePath
import com.ringle.xinpay.common.global.KEY_GLOBAL_NAV_CONTROLLER
import com.ringle.xinpay.common.util.LiveDataBus
import org.consenlabs.tokencore.wallet.KeystoreStorage
import org.consenlabs.tokencore.wallet.WalletManager
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.io.File

class TabWalletActivity : AppCompatActivity(), AnkoLogger, KeystoreStorage {
    /**
     *加载秘钥文件
     */
//    private val sdcardRoot= getExternalFilesDir("xinpay")?.absolutePath;
    override fun getKeystoreDir(): File {
        val path = getFilePath("xinpay")
        info { "sdcardRoot==$path" }
        return File(path)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        WalletManager.storage = this
        WalletManager.scanWallets()


    }

    private var isFirst = true
    override fun onStart() {
        super.onStart()
        if (isFirst) {
            isFirst = false
            mMainNavController = Navigation.findNavController(this,
                R.id.fragment_container
            )
            LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).value = mMainNavController
        }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
//        mMainNavController = Navigation.findNavController(
//            this,
//            R.id.fragment_container
//        )
//        LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).value = mMainNavController


    }

    private lateinit var mMainNavController: NavController


    override fun onSupportNavigateUp(): Boolean {

        return mMainNavController.navigateUp()
    }
}
