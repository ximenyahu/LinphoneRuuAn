package org.linphone.ruan.database

import android.content.Context
import androidx.room.Room
import org.linphone.ruan.mine.Account

private const val DATABASE_NAME = "contact_database"

class ContactRepository private constructor(context: Context) {

    private val database: ContactDatabase =
        Room.databaseBuilder(context.applicationContext, ContactDatabase::class.java, DATABASE_NAME)
            .build()

    private val contactDao = database.contactDao()

    fun getContacts(): List<Account> = contactDao.getContacts()

    fun getContact(id: Int): Account? = contactDao.getContact(id)

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
