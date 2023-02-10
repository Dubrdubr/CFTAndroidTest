package com.example.cftandroidtest.data.remote

import com.example.cftandroidtest.data.remote.dto.BinInfoDto
import com.example.cftandroidtest.util.Resource
import javax.inject.Inject

class BinClient @Inject constructor(
    private val binService: BinService
) {

    suspend fun getBinInfo(
        query: String
    ): BinInfoDto =
        binService.getBinInfo(
            query = query
        )
}