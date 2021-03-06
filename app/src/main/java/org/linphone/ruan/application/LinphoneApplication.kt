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
package org.linphone.ruan.application

import android.app.Application
import android.content.Context
import org.linphone.R
import org.linphone.core.CoreContext
import org.linphone.core.CorePreferences
import org.linphone.core.Factory
import org.linphone.core.LogCollectionState
import org.linphone.core.tools.Log
import org.linphone.ruan.database.ContactRepository

class LinphoneApplication : Application() {
    companion object {
        lateinit var corePreferences: CorePreferences
        lateinit var coreContext: CoreContext

        fun ensureCoreExists(context: Context, pushReceived: Boolean = false) {
            if (Companion::coreContext.isInitialized && !coreContext.stopped) {
                Log.d("[Application] Skipping Core creation (push received? $pushReceived)")
                return
            }

            Factory.instance().setLogCollectionPath(context.filesDir.absolutePath)
            Factory.instance().enableLogCollection(LogCollectionState.Enabled)

            corePreferences = CorePreferences(context)
            corePreferences.copyAssetsFromPackage()

            if (corePreferences.vfsEnabled) {
                CoreContext.activateVFS()
            }

            val config = Factory.instance().createConfigWithFactory(
                corePreferences.configPath,
                corePreferences.factoryConfigPath
            )
            corePreferences.config = config

            val appName = context.getString(R.string.app_name)
            Factory.instance().setDebugMode(corePreferences.debugLogs, appName)

            Log.i("[Application] Core context created ${if (pushReceived) "from push" else ""}")
            coreContext = CoreContext(context, config)
            coreContext.start()
        }
    }

    override fun onCreate() {
        super.onCreate()
        val appName = getString(R.string.app_name)
        android.util.Log.i("[$appName]", "Application is being created")
        ensureCoreExists(applicationContext)
        Log.i("[Application] Created")

        // Add by Simon 2021???4???25???15:07:17
        ContactRepository.initialize(this)
        // End
    }
}
