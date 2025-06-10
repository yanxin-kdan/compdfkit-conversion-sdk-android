package com.compdfkit.conversion.utils

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.util.Log
import androidx.core.content.ContextCompat

class PermissionStateManager(context: Context) {
    private val applicationContext = context.applicationContext

    // 检查所有文件访问权限状态
    fun checkManageStoragePermission(): Boolean {
//        val hasReadPermissions =
//            ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE) ==
//                    PackageManager.PERMISSION_GRANTED
//
//        val hasWritePermission =
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//                Environment.isExternalStorageManager()
//            } else {
//                ContextCompat.checkSelfPermission(applicationContext, READ_EXTERNAL_STORAGE) ==
//                        PackageManager.PERMISSION_GRANTED
//            }
//
//        Log.d("PermissionStateManager", "hasReadPermissions: $hasReadPermissions, hasWritePermission: $hasWritePermission")
//
//        return hasReadPermissions && hasWritePermission
        return true
    }
}