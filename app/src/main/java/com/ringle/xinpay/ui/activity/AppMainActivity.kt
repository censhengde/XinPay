package com.ringle.xinpay.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ringle.xinpay.app.R
import com.ringle.xinpay.common.global.KEY_GLOBAL_NAV_CONTROLLER
import com.ringle.xinpay.common.util.LiveDataBus

class AppMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_main)
    }

    private lateinit var mMainNavController: NavController
    override fun onResume() {
        super.onResume()
        mMainNavController = Navigation.findNavController(this,
            R.id.fragment_container_view
        )
        LiveDataBus.getChannel(KEY_GLOBAL_NAV_CONTROLLER).value = mMainNavController
    }

    override fun onSupportNavigateUp(): Boolean {

        return mMainNavController.navigateUp()
    }
}
