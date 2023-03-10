package com.example.cftandroidtest.domain.model


data class BinInfo(
    val cardNumber: String,
    val bank: Bank?,
    val brand: String?,
    val country: Country?,
    val number: Number?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?
)