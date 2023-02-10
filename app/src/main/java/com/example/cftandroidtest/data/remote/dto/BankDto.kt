package com.example.cftandroidtest.data.remote.dto


import com.example.cftandroidtest.domain.model.Bank
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BankDto(
    @Json(name = "city")
    val city: String?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "phone")
    val phone: String?,
    @Json(name = "url")
    val url: String?
)

fun BankDto.toBank() = Bank(
    city = city,
    name = name,
    phone = phone,
    url = url

)