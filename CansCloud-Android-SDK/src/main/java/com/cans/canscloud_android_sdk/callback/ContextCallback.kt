package com.cans.canscloud_android_sdk.callback

import androidx.annotation.Keep
import retrofit2.Call

interface ContextCallback {
    fun onIncomingReceived()

    fun onOutgoingStarted()

    fun onCallStarted()
}