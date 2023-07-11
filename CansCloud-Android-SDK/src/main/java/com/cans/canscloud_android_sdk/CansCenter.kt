package com.cans.canscloud_android_sdk

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.cans.canscloud_android_sdk.CansCloudApplication.Companion.coreContextCansBase
import com.cans.canscloud_android_sdk.CansCloudApplication.Companion.corePreferences
import org.linphone.core.AccountCreator
import org.linphone.core.Core
import org.linphone.core.CoreListenerStub
import org.linphone.core.Factory
import org.linphone.core.LogCollectionState
import org.linphone.core.LogLevel
import org.linphone.core.ProxyConfig
import org.linphone.core.RegistrationState
import org.linphone.core.TransportType
import org.linphone.core.tools.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList
import java.util.Locale

class CansCenter {
    companion object {

        private var proxyConfigToCheck: ProxyConfig? = null
        private lateinit var accountCreator: AccountCreator
        private var useGenericSipAccount: Boolean = false

        fun exToast(context: Context, text: String) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

        //----------*** Calling ***----------
        fun callConfig(context: Context) {
            CansCloudApplication.ensureCoreExists(context)
        }

        fun register() {
            var accountCreator = getAccountCreator(true)
            coreContextCansBase.core.addListener(coreListener)

            var username = "50104"
            var password = "p50104CNS"
            var domain = "test.cans.cc:8446"

            accountCreator.username = username
            accountCreator.password = password
            accountCreator.domain = domain
            accountCreator.displayName = ""
            accountCreator.transport = TransportType.Tcp

            val proxyConfig: ProxyConfig? = accountCreator.createProxyConfig()
            proxyConfigToCheck = proxyConfig

            if (proxyConfig == null) {
                Log.e("[Assistant] [Generic Login] Account creator couldn't create proxy config")
                coreContextCansBase.core.removeListener(coreListener)
                //  onErrorEvent.value = Event("Error: Failed to create account object")
                //waitForServerAnswer.value = false
                return
            }

            Log.i("[Assistant] [Generic Login] Proxy config created")
            // The following is required to keep the app alive
            // and be able to receive calls while in background
            if (domain.orEmpty() != corePreferences.defaultDomain) {
                Log.i(
                    "[Assistant] [Generic Login] Background mode with foreground service automatically enabled"
                )
                corePreferences.keepServiceAlive = true
                coreContextCansBase.notificationsManager.startForeground()
            }
        }

        fun checkDefaultAccount(): String {
            val defaultAccount =
                coreContextCansBase.core.defaultAccount?.params?.identityAddress?.username.toString()
            val domain =
                coreContextCansBase.core.defaultAccount?.params?.identityAddress?.domain.toString()

            return "${defaultAccount} ${domain}"
        }

        fun getAccountCreator(genericAccountCreator: Boolean = false): AccountCreator {

            coreContextCansBase.core.loadConfigFromXml(corePreferences.linphoneDefaultValuesPath)
            accountCreator =
                coreContextCansBase.core.createAccountCreator(corePreferences.xmlRpcServerUrl)
            accountCreator.language = Locale.getDefault().language

            if (genericAccountCreator != useGenericSipAccount) {
                accountCreator.reset()
                accountCreator.language = Locale.getDefault().language

                if (genericAccountCreator) {
                    Log.i("[Assistant] Loading default values")
                    coreContextCansBase.core.loadConfigFromXml(corePreferences.defaultValuesPath)
                } else {
                    Log.i("[Assistant] Loading linphone default values")
                    coreContextCansBase.core.loadConfigFromXml(corePreferences.linphoneDefaultValuesPath)
                }
                useGenericSipAccount = genericAccountCreator
            }
            return accountCreator
        }


        fun getCountCalls(): Int {
            val call = coreContextCansBase.core.callsNb
            Log.i("[Application] getCountCalls : $call")
            return call
        }

        fun startCall(addressToCall: String) {

            android.util.Log.d("MainActivity : ", "startCall")

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

        private val coreListener = object : CoreListenerStub() {
            override fun onRegistrationStateChanged(
                core: Core,
                cfg: ProxyConfig,
                state: RegistrationState,
                message: String
            ) {
                if (cfg == proxyConfigToCheck) {
                    Log.i("[Assistant] [Generic Login] Registration state is $state: $message")
                    if (state == RegistrationState.Ok) {
//                        waitForServerAnswer.value = false
//                        leaveAssistantEvent.value = Event(true)
                        core.removeListener(this)
                    } else if (state == RegistrationState.Failed) {
//                        waitForServerAnswer.value = false
//                        invalidCredentialsEvent.value = Event(true)
                        core.removeListener(this)
                    }
                }
            }
        }
    }
}