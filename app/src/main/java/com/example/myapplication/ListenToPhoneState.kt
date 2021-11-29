package com.example.myapplication

import android.app.Activity
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log


class ListenToPhoneState(private val activity: Activity) : PhoneStateListener() {

    private val callLogPermissionChecker = CallLogPermissionChecker()

    override fun onCallStateChanged(state: Int, number: String) {
        when (state) {
            TelephonyManager.CALL_STATE_IDLE -> {
                callLogPermissionChecker.isPermissionGranted(activity, number)
            }
            TelephonyManager.CALL_STATE_OFFHOOK -> Log.d(
                "SHIPIT",
                "State is OffHook:$number"
            )
            TelephonyManager.CALL_STATE_RINGING -> Log.d(
                "SHIPIT",
                "State is Ringing:$number"
            )
        }
    }
}