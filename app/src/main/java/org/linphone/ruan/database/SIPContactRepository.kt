package org.linphone.ruan.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import java.util.concurrent.Executors

private const val DATABASE_NAME = "contact_database"

class ContactRepository private constructor(context: Context) {

    private val databaseSIP: SIPContactDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            SIPContactDatabase::class.java,
            DATABASE_NAME
        )
            .build()

    private val executor = Executors.newSingleThreadExecutor()

    private val sipContactDao = databaseSIP.contactDao()

    //    fun getContacts(): List<Account> = contactDao.getContacts()
    fun getContacts(): LiveData<List<SIPAccount>> = sipContactDao.getSIPContacts()

    fun getContact(id: Int): SIPAccount? = sipContactDao.getSIPContact(id)

    fun updateSIPContact(sipAccount: SIPAccount) {
        executor.execute {
            sipContactDao.updateSIPContact(sipAccount)
        }
    }

    fun addSIPContact(sipAccount: SIPAccount) {
        executor.execute {
            sipContactDao.addSIPContact(sipAccount)
        }
    }

    companion object {
        private var INSTANCE: ContactRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = ContactRepository(context)
            }
        }

        fun get(): ContactRepository {
            return INSTANCE ?: throw IllegalStateException("ContactRepository must be initialize")
        }
    }
}
