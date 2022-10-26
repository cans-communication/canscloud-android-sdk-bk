package com.cans.canscloud_android_sdk

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.Keep
import com.cans.canscloud_android_sdk.LinphoneApplication.Companion.coreContext
import com.cans.canscloud_android_sdk.model.PhonebookContacts
import com.cans.canscloud_android_sdk.retrofit.ApiCallback
import com.cans.canscloud_android_sdk.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Keep
object CansCenter {
    fun exToast(context: Context, text : String){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show()
    }

    fun fetchPhonebook(domain: String, username: String, callback: ApiCallback<PhonebookContacts>) {
        RetrofitClient.service.getPhonebook(domain,username).enqueue(object : Callback<PhonebookContacts> {
            override fun onResponse(
                call: Call<PhonebookContacts>,
                response: Response<PhonebookContacts>
            ) {
               if (response.isSuccessful){
                   if (response.code() == 200) {
                       callback.onSuccess(response.body()!!)
                   }else{
                       callback.onError(Error(response.message()))
                   }
               }else{
                   callback.onError(Error(response.message()))
               }
            }

            override fun onFailure(call: Call<PhonebookContacts>, t: Throwable) {
                callback.onError(t)
            }
        })
    }

    fun startCall(addressToCall: String) {
        val addressToCall = addressToCall
        if (addressToCall.isNotEmpty()) {
            coreContext.startCall(addressToCall)
         //   eraseAll()
        } else {
            //setLastOutgoingCallAddress()
        }
    }

    fun terminateCall() {
       // coreContext.terminateCall(call)
    }

    private fun setLastOutgoingCallAddress() {
        val callLog = coreContext.core.lastOutgoingCallLog
        if (callLog != null) {
          //  enteredUri.value = LinphoneUtils.getDisplayableAddress(callLog.remoteAddress).substringBefore("@").substringAfter("sip:")
        }
    }
}