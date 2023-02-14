package com.example.cftandroidtest.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BinInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBinInfo(binInfoEntity: BinInfoEntity)

    @Query("SELECT card_number FROM bin_info ORDER BY id DESC")
    suspend fun getCardNumbersList(): List<String>

    @Query("SELECT * FROM bin_info WHERE card_number = :cardNumber")
    suspend fun getBinInfo(cardNumber: String): BinInfoEntity

    @Query("DELETE FROM bin_info WHERE card_number = :cardNumber")
    suspend fun delete(cardNumber: String)
}