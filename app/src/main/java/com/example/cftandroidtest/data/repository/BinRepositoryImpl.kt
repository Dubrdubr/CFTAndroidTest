package com.example.cftandroidtest.data.repository

import com.example.cftandroidtest.data.remote.BinClient
import com.example.cftandroidtest.data.remote.mapper.toDomain
import com.example.cftandroidtest.domain.model.BinInfo
import com.example.cftandroidtest.domain.repository.BinRepository
import com.example.cftandroidtest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BinRepositoryImpl @Inject constructor(
    private val binClient: BinClient
) : BinRepository {

    override fun getBinInfo(
        query: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String) -> Unit
    ): Flow<BinInfo?> = flow {
        try {
            val response = binClient.getBinInfo(query)
            emit(response.toDomain())
        } catch (e: HttpException) {
            e.printStackTrace()
            onError("[ApiResponse.Failure.HttpException](message=${e.message()})")
            emit(null)
        } catch (e: IOException) {
            e.printStackTrace()
            onError("[ApiResponse.Failure.IOException](message=${e.message})")
            emit(null)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }
}