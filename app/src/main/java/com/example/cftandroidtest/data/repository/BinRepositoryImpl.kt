package com.example.cftandroidtest.data.repository

import com.example.cftandroidtest.data.remote.BinClient
import com.example.cftandroidtest.data.remote.mapper.asDomain
import com.example.cftandroidtest.domain.model.BinInfo
import com.example.cftandroidtest.domain.repository.BinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinRepositoryImpl @Inject constructor(
    private val binClient: BinClient
) : BinRepository {

    override suspend fun getBinInfo(
        query: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit
    ): Flow<BinInfo?> = flow {
        try {
            val response = binClient.getBinInfo(query)
            emit(response.asDomain(query))
        } catch (e: HttpException) {
            e.printStackTrace()
            onError("[ApiResponse.Failure.HttpException](message=${e.message()})")
            emit(null)
        } catch (e: IOException) {
            e.printStackTrace()
            onError("[ApiResponse.Failure.IOException](message=${e.message})")
            emit(null)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)
}