package com.cans.canscloudandroidsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.TextView
import com.cans.canscloud_android_sdk.CansCenter

class MainActivity : AppCompatActivity() {
    var callback: CansCenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CansCenter.config(this, packageManager, packageName)
        CansCenter.register(this)
        var register = findViewById<TextView>(R.id.register)
        register.text = CansCenter.username()

        var button = findViewById<TextView>(R.id.button_Call)
        button.setOnClickListener {
            CansCenter.startCall("0838927729")
        }
    }

}