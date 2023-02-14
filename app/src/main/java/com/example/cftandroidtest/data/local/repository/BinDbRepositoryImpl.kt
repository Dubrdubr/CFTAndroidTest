package com.example.cftandroidtest.data.local.repository

import com.example.cftandroidtest.data.local.BinInfoDao
import com.example.cftandroidtest.data.local.mapper.asDomain
import com.example.cftandroidtest.data.local.mapper.asEntity
import com.example.cftandroidtest.domain.model.BinInfo
import com.example.cftandroidtest.domain.repository.BinDbRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinDbRepositoryImpl @Inject constructor(
    private val dao: BinInfoDao
) : BinDbRepository {

    override suspend fun insertBinInfo(binInfo: BinInfo) {
        dao.insertBinInfo(binInfo.asEntity())
    }

    override suspend fun getCardNumbersList(
        onStart: () -> Unit,
        onComplete: () -> Unit
    ): Flow<List<String>> = flow {
        val cardNumbersList = dao.getCardNumbersList()
        emit(cardNumbersList)
    }.onStart { onStart() }.onCompletion { onComplete() }

    override suspend fun getBinInfo(
        cardNumber: String,
        onStart: () -> Unit,
        onComplete: () -> Unit
    ): Flow<BinInfo> = flow {
        val binInfo = dao.getBinInfo(cardNumber)
        emit(binInfo.asDomain(cardNumber))
    }.onStart { onStart() }.onCompletion { onComplete() }

    override suspend fun deleteBinInfo(cardNumber: String) {
        dao.delete(cardNumber)
    }
}