package com.example.cftandroidtest.domain.mapper

interface DomainMapper<T, BinInfo> {

    fun toDomain(data: T): BinInfo

    fun fromDomain(model: BinInfo): T
}