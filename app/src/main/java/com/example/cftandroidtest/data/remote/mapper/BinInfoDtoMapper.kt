package com.example.cftandroidtest.data.remote.mapper

import com.example.cftandroidtest.data.remote.dto.BinInfoDto
import com.example.cftandroidtest.data.remote.dto.toBank
import com.example.cftandroidtest.data.remote.dto.toCountry
import com.example.cftandroidtest.data.remote.dto.toNumber
import com.example.cftandroidtest.domain.model.BinInfo

object BinInfoDtoMapper : DtoMapper<BinInfoDto, BinInfo> {

    override fun asDomain(data: BinInfoDto, query: String): BinInfo {
        return BinInfo(
            cardNumber = query,
            bank = data.bankDto?.toBank(),
            brand = data.brand,
            country = data.countryDto?.toCountry(),
            number = data.numberDto?.toNumber(),
            prepaid = data.prepaid,
            scheme = data.scheme,
            type = data.type
        )
    }
}

fun BinInfoDto.asDomain(query: String): BinInfo {
    return BinInfoDtoMapper.asDomain(this, query)
}