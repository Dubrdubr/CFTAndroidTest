package com.example.cftandroidtest.data.local.mapper

import com.example.cftandroidtest.data.local.BinInfoEntity
import com.example.cftandroidtest.domain.model.BinInfo

object BinInfoEntityMapper : EntityMapper<BinInfoEntity, BinInfo> {

    override fun asDomain(data: BinInfoEntity, query: String): BinInfo {
        return BinInfo(
            cardNumber = query,
            bank = data.bank,
            brand = data.brand,
            country = data.country,
            number = data.number,
            prepaid = data.prepaid,
            scheme = data.scheme,
            type = data.type
        )
    }

    override fun asEntity(model: BinInfo): BinInfoEntity {
        return BinInfoEntity(
            cardNumber = model.cardNumber,
            bank = model.bank,
            brand = model.brand,
            country = model.country,
            number = model.number,
            prepaid = model.prepaid,
            scheme = model.scheme,
            type = model.type
        )
    }
}

fun BinInfoEntity.asDomain(query: String) =
    BinInfoEntityMapper.asDomain(this, query)

fun BinInfo.asEntity() =
    BinInfoEntityMapper.asEntity(this)