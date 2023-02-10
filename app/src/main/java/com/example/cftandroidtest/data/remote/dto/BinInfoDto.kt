package com.example.cftandroidtest.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BinInfoDto(
    @Json(name = "bank")
    val bankDto: BankDto?,
    @Json(name = "brand")
    val brand: String?,
    @Json(name = "country")
    val countryDto: CountryDto?,
    @Json(name = "number")
    val numberDto: NumberDto?,
    @Json(name = "prepaid")
    val prepaid: Boolean?,
    @Json(name = "scheme")
    val scheme: String?,
    @Json(name = "type")
    val type: String?
)