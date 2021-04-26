package org.linphone.ruan.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SIPContactDao {

    @Query("SELECT * FROM sipaccount")
    fun getSIPContacts(): LiveData<List<SIPAccount>>

    @Query("SELECT * FROM sipaccount WHERE id=(:id)")
    fun getSIPContact(id: Int): SIPAccount

    @Update
    fun updateSIPContact(sipAccount: SIPAccount)

    @Insert
    fun addSIPContact(sipAccount: SIPAccount)
}
