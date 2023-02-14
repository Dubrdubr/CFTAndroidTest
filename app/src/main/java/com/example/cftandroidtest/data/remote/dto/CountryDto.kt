package com.example.cftandroidtest.data.remote.dto


import com.example.cftandroidtest.domain.model.Country
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountryDto(
    @Json(name = "alpha2")
    val alpha2: String?,
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "emoji")
    val emoji: String?,
    @Json(name = "latitude")
    val latitude: Float?,
    @Json(name = "longitude")
    val longitude: Float?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "numeric")
    val numeric: String?
)

fun CountryDto.toCountry() = Country(
    alpha2 = alpha2,
    currency = currency,
    emoji = emoji,
    latitude = latitude,
    longitude = longitude,
    name = name,
    numeric = numeric
)