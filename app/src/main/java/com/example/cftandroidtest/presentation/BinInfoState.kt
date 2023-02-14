package com.example.cftandroidtest.presentation

import com.example.cftandroidtest.domain.model.BinInfo

data class BinInfoState(
    val binInfo: BinInfo? = null,
    val cardNumbersList: List<String> = emptyList(),
    val infoIsLoading: Boolean = false,
    val listIsLoading: Boolean = false,
    val searchQuery: String = "",
    val message: String = ""
)