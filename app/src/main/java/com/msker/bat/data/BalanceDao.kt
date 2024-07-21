package com.msker.bat.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BalanceDao {
    @Insert
    fun insert(item: BalanceItemData)

    @Query("SELECT * FROM balance")
    fun getAll(): List<BalanceItemData>
}