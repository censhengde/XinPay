package com.ringle_al.common.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity() {


    protected val TAG = javaClass.simpleName



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun toActivity(targetClz: Class<out Activity>) {
        startActivity(Intent(this, targetClz))
    }


}