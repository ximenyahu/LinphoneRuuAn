package org.linphone.ruan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import org.linphone.R
import org.linphone.compatibility.Compatibility
import org.linphone.contact.ContactsUpdatedListenerStub
import org.linphone.core.tools.Log
import org.linphone.ruan.application.LinphoneApplication

class RuAnMainActivity : AppCompatActivity() {

    private val listener = object : ContactsUpdatedListenerStub() {
        override fun onContactsUpdated() {
            Log.i("[Main Activity] Contact(s) updated, update shortcuts")
            if (LinphoneApplication.corePreferences.contactsShortcuts) {
                Compatibility.createShortcutsToContacts(this@RuAnMainActivity)
            } else if (LinphoneApplication.corePreferences.chatRoomShortcuts) {
                Compatibility.createShortcutsToChatRooms(this@RuAnMainActivity)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ruan)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        /**
         * 设置顶部ActionBar
         */
//         val appBarConfiguration = AppBarConfiguration(setOf(
//         R.id.navigation_bench, R.id.navigation_message, R.id.navigation_contacts, R.id.navigation_mine))
//         setupActionBarWithNavController(navController, appBarConfiguration)

        // 底部导航区域显示模式
        navView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        navView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        LinphoneApplication.coreContext.contactsManager.addListener(listener)
    }

    override fun onPause() {
        LinphoneApplication.coreContext.contactsManager.removeListener(listener)
        super.onPause()
    }
}
