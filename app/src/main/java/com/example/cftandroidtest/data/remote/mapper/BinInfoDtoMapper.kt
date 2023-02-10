package com.example.cftandroidtest.data.remote.mapper

import com.example.cftandroidtest.data.remote.dto.BinInfoDto
import com.example.cftandroidtest.data.remote.dto.toBank
import com.example.cftandroidtest.data.remote.dto.toCountry
import com.example.cftandroidtest.data.remote.dto.toNumber
import com.example.cftandroidtest.domain.mapper.DomainMapper
import com.example.cftandroidtest.domain.model.BinInfo

object BinInfoDtoMapper : DomainMapper<BinInfoDto, BinInfo> {

    override fun toDomain(data: BinInfoDto): BinInfo {
        return BinInfo(
            bank = data.bankDto?.toBank(),
            brand = data.brand,
            country = data.countryDto?.toCountry(),
            number = data.numberDto?.toNumber(),
            prepaid = data.prepaid,
            scheme = data.scheme,
            type = data.type

        )
    }

    override fun fromDomain(model: BinInfo): BinInfoDto {
        TODO("Not yet implemented")
    }
}

fun BinInfoDto.toDomain(): BinInfo {
    return BinInfoDtoMapper.toDomain(this)
}