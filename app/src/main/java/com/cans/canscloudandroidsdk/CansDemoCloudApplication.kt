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
package com.cans.canscloudandroidsdk

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.lifecycle.LifecycleObserver
import com.cans.canscloud_android_sdk.CansCenter
import com.cans.canscloud_android_sdk.CansCenter.Companion.ensureCoreExists
import com.cans.canscloud_android_sdk.core.CoreContext
import com.cans.canscloud_android_sdk.core.CorePreferences
import com.cans.canscloud_android_sdk.utils.LinphoneUtils
import org.linphone.core.*

//import com.cans.canscloud_android_sdk.CansCloudApplication.Companion.corePreferences

class CansDemoCloudApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        val appName = getString(com.cans.canscloudandroidsdk.R.string.app_name)
        android.util.Log.i("[$appName]", "Application is being created")
        CansCenter.configCore(applicationContext)
    }
}
