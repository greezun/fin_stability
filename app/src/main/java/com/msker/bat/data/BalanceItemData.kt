package com.msker.bat.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance")
data class BalanceItemData(
    @PrimaryKey
    val time:Long = System.currentTimeMillis(),
    val sum:Double = 0.0,
    val descr: String = "",
    val isIncome:Boolean = false

)
