package com.msker.bat.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saving")
data class SavingsData(
    @PrimaryKey
    val time:Long = System.currentTimeMillis(),
    val sum:Double = 0.0,

)
