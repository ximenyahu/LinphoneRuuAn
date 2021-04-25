package org.linphone.ruan.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.linphone.ruan.mine.Account

@Database(entities = [Account::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao
}
