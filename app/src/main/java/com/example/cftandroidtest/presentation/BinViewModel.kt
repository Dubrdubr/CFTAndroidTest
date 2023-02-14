package com.example.cftandroidtest.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cftandroidtest.domain.model.BinInfo
import com.example.cftandroidtest.domain.repository.BinDbRepository
import com.example.cftandroidtest.domain.repository.BinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class BinViewModel @Inject constructor(
    private val repository: BinRepository,
    private val dbRepository: BinDbRepository
) : ViewModel() {

    var state by mutableStateOf(BinInfoState())

    private var searchJob: Job? = null

    init {
        getCardNumbersList()
    }

    fun onQueryChanged(query: String) {
        state = state.copy(searchQuery = query)
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(500)
            getBinInfo().join()
            val cardNumber = state.binInfo?.cardNumber
            if (cardNumber?.length != 8) return@launch
            if (state.cardNumbersList.contains(cardNumber)) {
                insertExistedBinInfo(state.binInfo!!).join()
            } else {
                dbRepository.insertBinInfo(state.binInfo!!)
            }
            getCardNumbersList()
        }

    }

    fun getBinInfoFromDb(cardNumber: String) {
        viewModelScope.launch {
            dbRepository.getBinInfo(
                cardNumber = cardNumber,
                onStart = { state = state.copy(infoIsLoading = true) },
                onComplete = { state = state.copy(infoIsLoading = false) }
            ).collectLatest {
                state = state.copy(binInfo = it, searchQuery = cardNumber)
            }
        }
    }

    private fun insertExistedBinInfo(binInfo: BinInfo): Job {
        return viewModelScope.launch {
            dbRepository.deleteBinInfo(binInfo.cardNumber)
            dbRepository.insertBinInfo(binInfo)
        }
    }

    private fun getCardNumbersList() {
        viewModelScope.launch {
            dbRepository.getCardNumbersList(
                onStart = { state = state.copy(listIsLoading = true) },
                onComplete = { state = state.copy(listIsLoading = false) }
            ).collectLatest {
                state = state.copy(cardNumbersList = it)
            }
        }
    }

    private fun getBinInfo(query: String = state.searchQuery): Job {
        return viewModelScope.launch {
            repository.getBinInfo(
                query,
                onStart = { state = state.copy(infoIsLoading = true) },
                onComplete = { state = state.copy(infoIsLoading = false) },
                onError = { state = state.copy(message = it) },
            ).collectLatest {
                state = state.copy(binInfo = it)
            }
        }
    }
}