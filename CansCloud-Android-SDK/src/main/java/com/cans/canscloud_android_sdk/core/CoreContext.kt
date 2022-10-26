/*
 * Copyright (c) 2010-2020 Belledonne Communications SARL.
 *
 * This file is part of linphone-android
 * (see https://www.linphone.org).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.cans.canscloud_android_sdk.core

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.telephony.TelephonyManager
import android.util.Base64
import android.util.Pair
import android.view.*
import androidx.lifecycle.MutableLiveData
import com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences
import com.cans.canscloud_android_sdk.utils.LinphoneUtils
import java.io.File
import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.KeyStore
import java.security.MessageDigest
import java.text.Collator
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec
import kotlin.concurrent.schedule
import kotlin.math.abs
import kotlinx.coroutines.*
import org.linphone.core.*
import org.linphone.core.tools.Log
import org.linphone.mediastream.Version

class CoreContext(val context: Context, coreConfig: Config) {
    var stopped = false
    val core: Core


    init {
        core = Factory.instance().createCoreWithConfig(coreConfig, context)
        Log.i("[Context] Ready")
    }

    fun terminateCall(call: Call) {
        Log.i("[Context] Terminating call $call")
        call.terminate()
    }

    fun startCall(to: String) {
        var stringAddress = to
//        if (android.util.Patterns.PHONE.matcher(to).matches()) {
//            val contact: Contact? = contactsManager.findContactByPhoneNumber(to)
//            val alias = contact?.getContactForPhoneNumberOrAddress(to)
//            if (alias != null) {
//                Log.i("[Context] Found matching alias $alias for phone number $to, using it")
//                stringAddress = alias
//            }
//        }

        val address: Address? = core.interpretUrl(stringAddress)
        if (address == null) {
            Log.e("[Context] Failed to parse $stringAddress, abort outgoing call")
            //callErrorMessageResourceId.value = Event(context.getString(R.string.call_error_network_unreachable))
            return
        }

        startCall(address)
    }

    fun startCall(address: Address, forceZRTP: Boolean = false, localAddress: Address? = null) {
        if (!core.isNetworkReachable) {
            Log.e("[Context] Network unreachable, abort outgoing call")
           // callErrorMessageResourceId.value = Event(context.getString(R.string.call_error_network_unreachable))
            return
        }

        val params = core.createCallParams(null)
        if (params == null) {
            val call = core.inviteAddress(address)
            Log.w("[Context] Starting call $call without params")
            return
        }

        if (forceZRTP) {
            params.mediaEncryption = MediaEncryption.ZRTP
        }
        if (LinphoneUtils.checkIfNetworkHasLowBandwidth(context)) {
            Log.w("[Context] Enabling low bandwidth mode!")
            params.isLowBandwidthEnabled = true
        }
        //params.recordFile = LinphoneUtils.getRecordingFilePathForAddress(address)

        if (localAddress != null) {
            params.proxyConfig = core.proxyConfigList.find { proxyConfig ->
                proxyConfig.identityAddress?.weakEqual(localAddress) ?: false
            }
            if (params.proxyConfig != null) {
                Log.i("[Context] Using proxy config matching address ${localAddress.asStringUriOnly()} as From")
            }
        }

        if (corePreferences.sendEarlyMedia) {
            params.isEarlyMediaSendingEnabled = true
        }

        val call = core.inviteAddressWithParams(address, params)
        Log.i("[Context] Starting call $call")
    }


}
