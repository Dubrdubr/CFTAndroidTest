package com.example.cftandroidtest.domain.repository

import com.example.cftandroidtest.domain.model.BinInfo
import kotlinx.coroutines.flow.Flow

interface BinRepository {

    suspend fun getBinInfo(
        query: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit,
    ): Flow<BinInfo?>


}