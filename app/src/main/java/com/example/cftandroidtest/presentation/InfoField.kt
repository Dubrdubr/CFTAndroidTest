package com.example.cftandroidtest.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
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
    state: BinInfoState
) {
    val binInfo = state.binInfo
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
    ) {
        if (state.infoIsLoading) {
            CircularProgressIndicator()
        }
        Row() {

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
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.weight(1f)
            ) {
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
        HyperlinkText(
            text = bank?.url ?: "asdf",
            link = bank?.url ?: ""
        )
        Text(
            text = bank?.phone ?: "?",
            fontWeight = FontWeight.Light,
            fontSize = 10.sp
        )
    }
}

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    text: String,
    link: String,
) {
    val annotatedString = buildAnnotatedString {
        append(text)

        val start = 0
        val end = text.indexOfLast { it == text.last() }

        addStyle(
            SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp
            ),
            start = start,
            end = end
        )
        addStringAnnotation(
            tag = "url",
            annotation = "https://$link",
            start = start,
            end = end

        )
    }
    val uriHandler = LocalUriHandler.current
    ClickableText(
        text = annotatedString,
        onClick = { offset ->
            val uri =
                annotatedString.getStringAnnotations("url", offset, offset).firstOrNull()?.item
            if (uri != null) {
                uriHandler.openUri(uri)
            }
        }
    )
}