package com.example.cftandroidtest.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cftandroidtest.domain.model.Bank
import com.example.cftandroidtest.domain.model.Country
import com.example.cftandroidtest.domain.model.Number

@Composable
fun InfoSection(
    modifier: Modifier = Modifier,
    viewModel: BinViewModel = hiltViewModel()
) {
    val binInfo = viewModel.state.binInfo

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            DefaultInfoTextField(
                header = "SCHEME / NETWORK",
                value = binInfo?.scheme ?: "?"
            )
            Spacer(modifier = Modifier.height(8.dp))
            DefaultInfoTextField(
                header = "BRAND",
                value = binInfo?.brand ?: "?"
            )
            Spacer(modifier = Modifier.height(8.dp))
            CardNumberTextField(binInfo?.number)
            Spacer(modifier = Modifier.height(8.dp))
            BankTextView(binInfo?.bank)
        }
        Spacer(modifier = Modifier.width(50.dp))
        Column(modifier = Modifier.weight(1f)) {
            DefaultInfoTextField(
                header = "type",
                value = binInfo?.type ?: "?"
            )
            Spacer(modifier = Modifier.height(8.dp))
            DefaultInfoTextField(
                header = "prepaid",
                value = when (binInfo?.prepaid) {
                    true -> {
                        "yes"
                    }
                    false -> {
                        "no"
                    }
                    else -> {
                        "?"
                    }
                }
            )
            Spacer(modifier = Modifier.height(8.dp))
            CountyTextField(binInfo?.country)
        }
    }
}

@Composable
fun DefaultInfoTextField(
    modifier: Modifier = Modifier,
    header: String,
    value: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = header.uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = (0f).sp
        )
        Text(
            text = value.replaceFirstChar { it.uppercase() },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CardNumberTextField(
    cardNumber: Number?
) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max)
    ) {
        Text(
            text = "Card number".uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)) {
                Text(
                    text = "length".uppercase(),
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp
                )
                Text(
                    text = cardNumber?.length?.toString() ?: "?",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "luhn".uppercase(),
                    fontWeight = FontWeight.Light,
                    fontSize = 10.sp
                )
                Text(
                    text = when (cardNumber?.luhn) {
                        true -> {
                            "Yes"
                        }
                        false -> {
                            "No"
                        }
                        else -> {
                            "?"
                        }
                    },
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun CountyTextField(
    country: Country?
) {
    Column {
        Text(
            text = "Country".uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = country?.name ?: "?",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "(latitude: ${country?.latitude ?: "?"}, longitude: ${country?.longitude ?: "?"})",
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
    }
}

@Composable
fun BankTextView(
    bank: Bank?
) {
    Column {
        Text(
            text = "Bank".uppercase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = "${bank?.name ?: "Name: ?"}, ${bank?.city ?: "City: ?"}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = bank?.url ?: "?",
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
        Text(
            text = bank?.phone ?: "?",
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    InfoSection()
}