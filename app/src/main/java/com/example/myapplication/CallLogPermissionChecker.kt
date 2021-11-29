package com.example.myapplication

import android.app.Activity
import android.content.ContentValues
import android.net.Uri
import android.provider.CallLog
import android.util.Log


class CallLogPermissionChecker {

    fun isPermissionGranted(activity: Activity, number: String): Boolean {
        return try {
            checkWriteCallLog(activity, number)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(CALL_LOG_TAG, "throwing exception in PermissionChecker:  ", e)
            false
        }
    }

    private fun checkWriteCallLog(activity: Activity, number: String): Boolean {
        val contentResolver = activity.contentResolver
        val content = ContentValues()
        content.put(CallLog.Calls.NUMBER, number)
        contentResolver.insert(Uri.parse("content://call_log/calls"), content)
        contentResolver.delete(
            Uri.parse("content://call_log/calls"),
            "number = ?",
            arrayOf(number)
        )
        return true
    }

    companion object {
        const val CALL_LOG_TAG = "CallLog"
    }
}