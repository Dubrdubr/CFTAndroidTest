package com.example.cftandroidtest.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cftandroidtest.domain.model.Bank
import com.example.cftandroidtest.domain.model.Country
import com.example.cftandroidtest.domain.model.Number

@Entity(tableName = "bin_info")
data class BinInfoEntity(
    @PrimaryKey
    @ColumnInfo(name = "card_number")
    val cardNumber: String,
    val bank: Bank?,
    val brand: String?,
    val country: Country?,
    val number: Number?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
)