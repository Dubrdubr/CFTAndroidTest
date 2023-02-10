package com.example.cftandroidtest.data.remote.dto


import com.example.cftandroidtest.domain.model.Number
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NumberDto(
    @Json(name = "length")
    val length: Int?,
    @Json(name = "luhn")
    val luhn: Boolean?
)

fun NumberDto.toNumber() = Number(
    length = length,
    luhn = luhn
)