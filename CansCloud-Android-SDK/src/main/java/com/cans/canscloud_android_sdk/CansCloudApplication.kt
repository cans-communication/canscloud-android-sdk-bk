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
import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import com.cans.canscloud_android_sdk.CansCenter.Companion.ensureCoreExists
import com.cans.canscloud_android_sdk.core.CoreContext
import com.cans.canscloud_android_sdk.core.CorePreferences
import com.cans.canscloud_android_sdk.utils.LinphoneUtils
import org.linphone.core.*
import org.linphone.core.tools.Log
import org.linphone.core.tools.service.CoreService
import org.linphone.mediastream.Version
//import com.cans.canscloud_android_sdk.CansCloudApplication.Companion.corePreferences

class CansCloudApplication : Application(), LifecycleObserver {
//    companion object {

//        fun ensureCoreExists(context: Context, pushReceived: Boolean = false) {
//            if (com.cans.canscloud_android_sdk.CansCloudApplication.Companion::coreContext.isInitialized && !com.cans.canscloud_android_sdk.CansCloudApplication.Companion.coreContext.stopped) {
//                Log.d("[Application] Skipping Core creation (push received? $pushReceived)")
//                return
//            }
//
//            Factory.instance().setLogCollectionPath(context.filesDir.absolutePath)
//            Factory.instance().enableLogCollection(LogCollectionState.Enabled)
//
//            corePreferences = CorePreferences(context)
//          //  corePreferences.copyAssetsFromPackage()
//
//            if (corePreferences.vfsEnabled) {
//                CoreContext.activateVFS()
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
//            coreContext = CoreContext(context, config)
//            coreContext.start()
//        }
//    }


//    override fun newImageLoader(): ImageLoader {
//        return ImageLoader.Builder(this)
//            .components {
//                add(VideoFrameDecoder.Factory())
//                add(SvgDecoder.Factory())
//                if (Version.sdkAboveOrEqual(Version.API28_PIE_90)) {
//                    add(ImageDecoderDecoder.Factory())
//                } else {
//                    add(GifDecoder.Factory())
//                }
//            }
//            .memoryCache {
//                MemoryCache.Builder(this)
//                    .maxSizePercent(0.25)
//                    .build()
//            }
//            .diskCache {
//                DiskCache.Builder()
//                    .directory(cacheDir.resolve("image_cache"))
//                    .maxSizePercent(0.02)
//                    .build()
//            }
//            .build()
//    }
}
