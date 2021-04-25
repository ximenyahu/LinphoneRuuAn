package org.linphone.ruan.mine

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var password: String,
    var phoneNumber: Int,
    var sipId: String
)
