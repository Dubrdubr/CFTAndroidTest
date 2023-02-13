package com.example.cftandroidtest.data.remote.mapper

import com.example.cftandroidtest.data.remote.dto.BinInfoDto

interface DtoMapper<T, BinInfo> {

    fun asDomain(data: BinInfoDto, query: String): BinInfo
}