package com.example.cftandroidtest.domain.repository

import com.example.cftandroidtest.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface BinDbRepository {

    suspend fun insertBinInfo(
        entity: BinInfo
    )

    suspend fun getCardNumbersList(
        onStart: () -> Unit,
        onComplete: () -> Unit
    ): Flow<List<String>>

    suspend fun getBinInfo(
        cardNumber: String,
        onStart: () -> Unit,
        onComplete: () -> Unit
    ): Flow<BinInfo>
}