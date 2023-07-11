package com.cans.canscloudandroidsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import com.cans.canscloud_android_sdk.CansCenter

class MainActivity : AppCompatActivity() {
    var callback: CansCenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CansCenter.exToast(this, "Hello World")

        var count = CansCenter.getCountCalls()
        Log.d("MainActivity : ","count calls: $count")

        CansCenter.startCall("0838927729")

    }

}