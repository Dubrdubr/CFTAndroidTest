package com.example.cftandroidtest.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun CardNumberListItem(
    cardNumber: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = cardNumber,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold
    )
}