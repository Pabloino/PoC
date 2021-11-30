package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {

    var mReceiver: PhoneStateListener = ListenToPhoneState(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(mReceiver, PhoneStateListener.LISTEN_CALL_STATE);

        val callButton = findViewById<Button>(R.id.button)

        callButton.setOnClickListener {
            requestPermissions()
            if (checkCallLogPermissions()) {
                val callIntent = Intent(Intent.ACTION_DIAL)
                val number = "1122334455"
                callIntent.data = Uri.parse("tel:$number")
                startActivity(callIntent)
            }
        }
    }

    private fun checkPermission(permission: String): Boolean {
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    private fun checkCallLogPermissions(): Boolean {
        return checkPermission(Manifest.permission.WRITE_CALL_LOG)
                && checkPermission(Manifest.permission.READ_CALL_LOG)
                && checkPermission(Manifest.permission.READ_PHONE_STATE)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_CALL_LOG, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_PHONE_STATE), 0)
    }

}