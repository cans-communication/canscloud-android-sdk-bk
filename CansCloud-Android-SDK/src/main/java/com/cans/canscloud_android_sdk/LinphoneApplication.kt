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
package com.cans.canscloud_android_sdk

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.cans.canscloud_android_sdk.core.CoreContext
import com.cans.canscloud_android_sdk.core.CorePreferences
import org.linphone.core.*
import org.linphone.core.tools.Log

class LinphoneApplication : Application(), LifecycleObserver {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var corePreferences: CorePreferences
        @SuppressLint("StaticFieldLeak")
        lateinit var coreContext: CoreContext

        fun ensureCoreExists(context: Context, pushReceived: Boolean = false) {
            if ( com.cans.canscloud_android_sdk.LinphoneApplication.Companion::coreContext.isInitialized && ! com.cans.canscloud_android_sdk.LinphoneApplication.Companion.coreContext.stopped) {
                Log.d("[Application] Skipping Core creation (push received? $pushReceived)")
                return
            }

            Factory.instance().setLogCollectionPath(context.filesDir.absolutePath)
            Factory.instance().enableLogCollection(LogCollectionState.Enabled)

            com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences = CorePreferences(context)
            com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences.copyAssetsFromPackage()

//            if ( com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences.vfsEnabled) {
//                CoreContext.activateVFS()
//            }

            val config = Factory.instance().createConfigWithFactory( com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences.configPath, com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences.factoryConfigPath)
            com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences.config = config

            val appName = context.getString( com.cans.canscloud_android_sdk.R.string.app_name)
            Factory.instance().setLoggerDomain(appName)
            Factory.instance().enableLogcatLogs( com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences.logcatLogsOutput)
            if ( com.cans.canscloud_android_sdk.LinphoneApplication.Companion.corePreferences.debugLogs) {
                Factory.instance().loggingService.setLogLevel(LogLevel.Message)
            }

            Log.i("[Application] Core context created ${if (pushReceived) "from push" else ""}")
            com.cans.canscloud_android_sdk.LinphoneApplication.Companion.coreContext = CoreContext(context, config)
            com.cans.canscloud_android_sdk.LinphoneApplication.Companion.coreContext.start()
        }
    }

    override fun onCreate() {
        super.onCreate()
        val appName = getString( com.cans.canscloud_android_sdk.R.string.app_name)
        android.util.Log.i("[$appName]", "Application is being created")
        com.cans.canscloud_android_sdk.LinphoneApplication.Companion.ensureCoreExists(applicationContext)
        Log.i("[Application] Created")
    }
}
