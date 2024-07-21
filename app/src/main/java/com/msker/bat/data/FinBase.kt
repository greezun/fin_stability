package com.msker.bat.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SavingsData::class, BalanceItemData::class], version = 1)
abstract class FinBase: RoomDatabase() {
    abstract fun getBalanceDao():BalanceDao
    abstract fun getSavingDao():SavingDao
}