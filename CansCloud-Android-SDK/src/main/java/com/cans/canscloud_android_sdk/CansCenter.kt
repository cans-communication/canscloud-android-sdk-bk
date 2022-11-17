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

class CansCenter {
    companion object {

//        @SuppressLint("StaticFieldLeak")
//        lateinit var corePreferences: CorePreferences
//        @SuppressLint("StaticFieldLeak")
//        lateinit var coreContext: CoreContextCansBase
//
//        fun ensureCoreExists(context: Context, pushReceived: Boolean = false) {
//            if (::coreContext.isInitialized && !coreContext.stopped) {
//                Log.d("[Application] Skipping Core creation (push received? $pushReceived)")
//                return
//            }
//
//            Factory.instance().setLogCollectionPath(context.filesDir.absolutePath)
//            Factory.instance().enableLogCollection(LogCollectionState.Enabled)
//
//            corePreferences = CorePreferences(context)
//            //  corePreferences.copyAssetsFromPackage()
//
//            if (corePreferences.vfsEnabled) {
//                CoreContextCansBase.activateVFS()
//            }
//
//            val config = Factory.instance().createConfigWithFactory(corePreferences.configPath, corePreferences.factoryConfigPath)
//            corePreferences.config = config
//
//            val appName = context.getString(R.string.app_name)
//            Factory.instance().setLoggerDomain(appName)
//            Factory.instance().enableLogcatLogs(corePreferences.logcatLogsOutput)
//            if (corePreferences.debugLogs) {
//                Factory.instance().loggingService.setLogLevel(LogLevel.Message)
//            }
//
//            Log.i("[Application] Core context created ${if (pushReceived) "from push" else ""}")
//            coreContext = CoreContextCansBase(context, config)
//            coreContext.start()
//        }

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

        fun startCall(addressToCall: String) {
            val addressToCall = addressToCall
            if (addressToCall.isNotEmpty()) {
                coreContextCansBase.startCall(addressToCall)
                //   eraseAll()
            } else {
                //setLastOutgoingCallAddress()
            }
        }

        fun callConfig(context: Context){
            CansCloudApplication.ensureCoreExists(context)
        }





        fun getCountCalls(): Int {
            val call = coreContextCansBase.core.callsNb
            Log.i("[Application] getCountCalls : $call")
            return call
        }

        fun terminateCall() {
            // coreContext.terminateCall(call)
        }

        private fun setLastOutgoingCallAddress() {
            val callLog = coreContextCansBase.core.lastOutgoingCallLog
            if (callLog != null) {
                //  enteredUri.value = LinphoneUtils.getDisplayableAddress(callLog.remoteAddress).substringBefore("@").substringAfter("sip:")
            }
        }
    }
}