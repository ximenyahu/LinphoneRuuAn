package org.linphone.ruan.database

import androidx.room.Dao
import androidx.room.Query
import org.linphone.ruan.mine.Account

@Dao
interface ContactDao {

    @Query("SELECT * FROM account")
    fun getContacts(): List<Account>

    @Query("SELECT * FROM account WHERE id=(:id)")
    fun getContact(id: Int): Account
}
