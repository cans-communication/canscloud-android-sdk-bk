package com.cans.canscloud_android_sdk.retrofit

import androidx.annotation.Keep
import retrofit2.Call

interface ApiCallback<T> {
    fun onError(exception: Throwable)

    fun onSuccess(response: T)
}