package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager


class MainActivity : AppCompatActivity() {

    var mReceiver: PhoneStateListener = ListenToPhoneState(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(mReceiver, PhoneStateListener.LISTEN_CALL_STATE);

        val callButton = findViewById<Button>(R.id.button)

        callButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL);
            val number = "1122334455"
            callIntent.data = Uri.parse("tel:$number")
            startActivity(callIntent)
        }
    }

}