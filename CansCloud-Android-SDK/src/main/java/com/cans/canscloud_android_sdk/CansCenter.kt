package com.cans.canscloud_android_sdk

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.cans.canscloud_android_sdk.CansCloudApplication.Companion.coreContextCansBase
import com.cans.canscloud_android_sdk.callback.ContextCallback
import com.cans.canscloud_android_sdk.core.CoreContextCansBase
import com.cans.canscloud_android_sdk.core.CorePreferences
import com.cans.canscloud_android_sdk.model.PhonebookContacts
import com.cans.canscloud_android_sdk.retrofit.ApiCallback
import com.cans.canscloud_android_sdk.retrofit.RetrofitClient
import org.linphone.core.CoreListenerStub
import org.linphone.core.Factory
import org.linphone.core.LogCollectionState
import org.linphone.core.LogLevel
import org.linphone.core.tools.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class CansCenter {
    companion object {

        fun exToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

        fun fetchPhonebook(
            domain: String,
            username: String,
            callback: ApiCallback<PhonebookContacts>
        ) {
            RetrofitClient.service.getPhonebook(domain, username)
                .enqueue(object : Callback<PhonebookContacts> {
                    override fun onResponse(
                        call: Call<PhonebookContacts>,
                        response: Response<PhonebookContacts>
                    ) {
                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                callback.onSuccess(response.body()!!)
                            } else {
                                callback.onError(Error(response.message()))
                            }
                        } else {
                            callback.onError(Error(response.message()))
                        }
                    }

                    override fun onFailure(call: Call<PhonebookContacts>, t: Throwable) {
                        callback.onError(t)
                    }
                })
        }
//----------*** Calling ***----------
        fun callConfig(context: Context){
            CansCloudApplication.ensureCoreExists(context)
        }

        fun registerListenerCall(listener: ContextCallback){
            CoreContextCansBase.CallbackListeners.registerListener(listener)
        }

        fun getCountCalls(): Int {
            val call = coreContextCansBase.core.callsNb
            Log.i("[Application] getCountCalls : $call")
            return call
        }

        fun startCall(addressToCall: String) {

            android.util.Log.d("MainActivity : ","startCall")

            val addressToCall = addressToCall
            if (addressToCall.isNotEmpty()) {
                coreContextCansBase.startCall(addressToCall)
                //   eraseAll()
            } else {
                //setLastOutgoingCallAddress()
            }
        }

        fun terminateCall() {
          //  coreContextCansBase.terminateCall(call)
        }

        private fun setLastOutgoingCallAddress() {
            val callLog = coreContextCansBase.core.lastOutgoingCallLog
            if (callLog != null) {
                //  enteredUri.value = LinphoneUtils.getDisplayableAddress(callLog.remoteAddress).substringBefore("@").substringAfter("sip:")
            }
        }

        private val listeners: MutableSet<ContextCallback> = mutableSetOf()
        fun registerListener(listener: ContextCallback) {
            listeners.add(listener)
        }

        fun unRegisterListener(listener: ContextCallback) {
            listeners.remove(listener)
        }
    }
}