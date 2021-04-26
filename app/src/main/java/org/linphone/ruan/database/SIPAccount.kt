package org.linphone.ruan.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SIPAccount(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var password: String,
    var phoneNumber: Int,
    var sipId: String
)
