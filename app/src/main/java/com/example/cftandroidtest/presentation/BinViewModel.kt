package com.example.cftandroidtest.presentation

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cftandroidtest.domain.repository.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val repository: BinRepository
) : ViewModel() {

    var state by mutableStateOf(BinInfoState())

    private var searchJob: Job? = null

    fun onQueryChanged(query: String) {
        state = state.copy(searchQuery = query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getBinInfo()
        }
    }

    private fun getBinInfo(query: String = state.searchQuery) {
        viewModelScope.launch {
            repository.getBinInfo(
                query,
                onStart = { state = state.copy(isLoading = true) },
                onComplete = { state = state.copy(isLoading = false) },
                onError = { state = state.copy(message = it) },
            ).collectLatest {
                state = state.copy(binInfo = it)
            }
        }
    }
}