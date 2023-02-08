package com.example.cftandroidtest.data.remote.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Number(
    @Json(name = "length")
    val length: Int,
    @Json(name = "luhn")
    val luhn: Boolean
)