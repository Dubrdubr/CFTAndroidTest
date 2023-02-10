package com.example.cftandroidtest.data.remote

import com.example.cftandroidtest.data.remote.dto.BinInfoDto
import com.example.cftandroidtest.util.Resource
import retrofit2.http.GET
import retrofit2.http.Path

interface BinService {

    @GET("/{query}")
    suspend fun getBinInfo(
        @Path("query") query: String
    ): BinInfoDto
}