package org.linphone.ruan.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SIPAccount::class], version = 1)
abstract class SIPContactDatabase : RoomDatabase() {
    abstract fun contactDao(): SIPContactDao
}
