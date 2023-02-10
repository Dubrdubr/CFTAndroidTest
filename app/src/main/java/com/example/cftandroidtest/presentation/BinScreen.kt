package com.example.cftandroidtest.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun BinScreen(
    modifier: Modifier = Modifier,
    viewModel: BinViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Column(
        modifier = modifier
    ) {
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                viewModel.onQueryChanged(it)
            }
        )
        Text(text = state.binInfo?.scheme ?: "?")
        Text(text = state.binInfo?.brand ?: "?")
        Text(text = state.binInfo?.type ?: "?")
    }
}