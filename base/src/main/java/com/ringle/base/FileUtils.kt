package com.ringle.base

import android.content.Context
import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED
import java.io.File

/**
 * create by 岑胜德
 * on 2019/12/16
 * 说明:
 *
 */
//fun Any.getSdcardRootDir():String{
//
//}
fun Context.getFilePath(dir: String): String {
    //判断SD卡是否可用 
    return if (MEDIA_MOUNTED == Environment.getExternalStorageState()) {
        getExternalFilesDir(dir)?.absolutePath!!
        // directoryPath =context.getExternalCacheDir().getAbsolutePath() ;  
    } else {
        //没内存卡就存机身内存  
        "$filesDir${File.separator}$dir"
        // directoryPath=context.getCacheDir()+File.separator+dir;
    }
}