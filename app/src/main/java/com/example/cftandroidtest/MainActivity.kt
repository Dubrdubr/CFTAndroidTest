package com.example.cftandroidtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cftandroidtest.presentation.BinViewModel
import com.example.cftandroidtest.presentation.CardNumberListItem
import com.example.cftandroidtest.presentation.HyperlinkText
import com.example.cftandroidtest.presentation.InfoSection
import com.example.cftandroidtest.ui.theme.CFTAndroidTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CFTAndroidTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val viewModel: BinViewModel = hiltViewModel()
                    val state = viewModel.state
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        OutlinedTextField(
                            value = state.searchQuery,
                            onValueChange = {
                                viewModel.onQueryChanged(it)
                            },
                            maxLines = 1,
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        InfoSection(state = state)
                        Spacer(modifier = Modifier.height(16.dp))
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                        ) {
                            items(state.cardNumbersList.size) { i ->
                                val cardNumber = state.cardNumbersList[i]
                                CardNumberListItem(
                                    cardNumber = cardNumber,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.getBinInfoFromDb(cardNumber)
                                        }
                                        .padding(8.dp)
                                )
                                if (i < state.cardNumbersList.size) {
                                    Divider(modifier = Modifier.padding(horizontal = 8.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}