package com.example.obligatorisk_opg_2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obligatorisk_opg_2.BirthdayUIState
import com.example.obligatorisk_opg_2.SingleBirthdayUIState
import com.example.obligatorisk_opg_2.data.Birthday
import com.example.obligatorisk_opg_2.data.BirthdayRepository
import com.example.obligatorisk_opg_2.data.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BirthdayViewModel (
    private val BirthdayRepository: BirthdayRepository
) : ViewModel() {
    private val _birthdayUIState = MutableStateFlow(BirthdayUIState())
    val birthdayUIState: StateFlow<BirthdayUIState> = _birthdayUIState

    private val _singleBirthdayUIState = MutableStateFlow(SingleBirthdayUIState())

    private var originalBirthdayList: List<Birthday> = emptyList()

    init {
        getBirthdays()
    }

    fun getBirthdays() {
        _birthdayUIState.update {it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val result = BirthdayRepository.getBirthdays()) {
                is NetworkResult.Success -> {
                    originalBirthdayList = result.data
                    _birthdayUIState.update { uiState ->
                        uiState.copy(isLoading = false, birthdays = result.data)
                    }
                }

                is NetworkResult.Error -> {
                    _birthdayUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }




}