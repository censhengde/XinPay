package com.ringle.xinpay.wallet.ui.common

import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.ringle.xinpay.wallet.R

/**
 * create by 岑胜德
 * on 2019/12/27
 * 说明:
 *
 */

fun showInputDialog(
    context: Context,
    cancel: (() -> Unit)? = null,
    confirm: ((String) -> Unit)? = null
) {
    val dialog = AlertDialog.Builder(context).create()
    dialog.show()
    dialog.window?.setContentView(R.layout.dialog_input_password)
    dialog.window?.setGravity(Gravity.CENTER)
    dialog.window?.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
    dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
    val decorView = dialog.window?.decorView
    val paddingTop = decorView?.paddingTop
    val paddingBottom = decorView?.paddingBottom
    val paddingLeft = decorView?.paddingLeft;
    val paddingRight = decorView?.paddingRight

    val width = (800 + paddingLeft!! + paddingRight!!)
    val height = 500 + paddingTop!! + paddingBottom!!
    dialog.window?.setLayout(width, height)
    val et = dialog.findViewById<EditText>(R.id.et_input_password)
    et?.requestFocus()
    val tvCancel = dialog.findViewById<TextView>(R.id.tv_cancel)
    val tvConfirm = dialog.findViewById<TextView>(R.id.tv_confirm)
    //取消
    tvCancel?.setOnClickListener {
        dialog.dismiss()
        cancel?.invoke()
    }
    //确定
    tvConfirm?.setOnClickListener {
        val password = et?.text.toString()
        dialog.dismiss()
        confirm?.invoke(password)
    }

}

class InputPasswordDialog {
    companion object {
        operator fun invoke(
            context: Context,
            cancel: (() -> Unit)? = null,
            confirm: ((String) -> Unit)? = null
        ) {
            val dialog = AlertDialog.Builder(context).create()
            dialog.show()
            dialog.window?.setContentView(R.layout.dialog_input_password)
            dialog.window?.setGravity(Gravity.CENTER)
            val et = dialog.findViewById<EditText>(R.id.et_input_password)
            val tvCancel = dialog.findViewById<TextView>(R.id.tv_cancel)
            val tvConfirm = dialog.findViewById<EditText>(R.id.tv_confirm)
            //取消
            tvCancel?.setOnClickListener {
                dialog.dismiss()
                cancel?.invoke()
            }
            //确定
            tvConfirm?.setOnClickListener {
                val password = et?.text.toString()
                dialog.dismiss()
                confirm?.invoke(password)
            }
        }
    }
}