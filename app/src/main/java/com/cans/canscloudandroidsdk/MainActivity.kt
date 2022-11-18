package com.cans.canscloudandroidsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import com.cans.canscloud_android_sdk.CansCenter
import com.cans.canscloud_android_sdk.callback.ContextCallback
import com.cans.canscloud_android_sdk.model.PhonebookContacts
import com.cans.canscloud_android_sdk.retrofit.ApiCallback

class MainActivity : AppCompatActivity(),ContextCallback {
    var callback: CansCenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CansCenter.registerListenerCall(this)
        CansCenter.exToast(this, "Hello World")
        CansCenter.fetchPhonebook("cns.cans.cc","39706", object : ApiCallback<PhonebookContacts> {
            override fun onSuccess(response: PhonebookContacts) {
                Log.d("getPhonebook onSuccess","MainActivity: $response")
            }

            override fun onError(exception: Throwable) {
                Log.d("getPhonebook onFailure","MainActivity: $exception")
            }
        })

        var count = CansCenter.getCountCalls()
        Log.d("MainActivity : ","count calls: $count")

        CansCenter.startCall("0838927729")

    }

    override fun onIncomingReceived() {
        Log.d("MainActivity : ","onIncomingReceived")

    }

    override fun onOutgoingStarted() {
        Log.d("MainActivity : ","onOutgoingStarted")

    }

    override fun onCallStarted() {
        Log.d("MainActivity : ","onCallStarted")

    }
}