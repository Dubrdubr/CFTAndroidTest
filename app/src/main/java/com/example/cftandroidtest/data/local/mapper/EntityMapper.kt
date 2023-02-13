package com.example.cftandroidtest.data.local.mapper

interface EntityMapper<T, BinInfo> {

    fun asDomain(data: T, query: String): BinInfo

    fun asEntity(model: BinInfo): T
}