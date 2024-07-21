package com.msker.bat.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SavingDao {
    @Insert
    fun insert(item: SavingsData)

    @Query("SELECT * FROM saving")
    fun getAll(): List<SavingsData>

    @Query("DELETE FROM saving")
    fun deleteAll()
}