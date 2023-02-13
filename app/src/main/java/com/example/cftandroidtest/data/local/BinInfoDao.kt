package com.example.cftandroidtest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinInfo(binInfoEntity: BinInfoEntity)

    @Query("SELECT card_number FROM bin_info")
    suspend fun getCardNumbersList(): List<String>

    @Query("SELECT * FROM bin_info WHERE card_number = :cardNumber")
    suspend fun getBinInfo(cardNumber: String): BinInfoEntity
}