package com.example.obligatorisk_opg_2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.obligatorisk_opg_2.BirthdayUIState
import com.example.obligatorisk_opg_2.SingleBirthdayUIState
import com.example.obligatorisk_opg_2.data.Birthday
import com.example.obligatorisk_opg_2.data.BirthdayRepository
import com.example.obligatorisk_opg_2.data.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

enum class SortBy {
    NONE, NAME, AGE, BIRTHDAY
}

class BirthdayViewModel (
    private val BirthdayRepository: BirthdayRepository
) : ViewModel() {
    private val _birthdayUIState = MutableStateFlow(BirthdayUIState())
    val birthdayUIState: StateFlow<BirthdayUIState> = _birthdayUIState

    private val _singleBirthdayUIState = MutableStateFlow(SingleBirthdayUIState())
    val singleBirthdayUIState: StateFlow<SingleBirthdayUIState> = _singleBirthdayUIState.asStateFlow()

    private var originalBirthdayList: List<Birthday> = emptyList()

    private val _selectedBirthday = MutableStateFlow<Birthday?>(null)
    val selectedBirthday: StateFlow<Birthday?> = _selectedBirthday.asStateFlow()

    private var currentSearchQuery: String = ""
    private var currentSortBy: SortBy = SortBy.NONE

//    init {
//        getBirthdays(userId = birthdayUIState.value.birthdays[0].userId)
//    }

    fun selectBirthday(birthday: Birthday) {
        _selectedBirthday.value = birthday
    }

    fun getBirthdays() {
        _birthdayUIState.update {it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val result = BirthdayRepository.getBirthdays()) {
                is NetworkResult.Success -> {
                    originalBirthdayList = result.data
                    applyFilterAndSort()
                }

                is NetworkResult.Error -> {
                    _birthdayUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun getBirthdays(userId: String) {
        Log.d("BirthdayViewModel", "getBirthdays called with userId: $userId")
        _birthdayUIState.update {it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            when (val result = BirthdayRepository.getBirthdays(userId)) {
                is NetworkResult.Success -> {
                    originalBirthdayList = result.data
                    applyFilterAndSort()
                }

                is NetworkResult.Error -> {
                    _birthdayUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun filterAndSort(query: String, sortBy: SortBy) {
        currentSearchQuery = query
        currentSortBy = sortBy
        applyFilterAndSort()
    }

    private fun applyFilterAndSort() {
        var filteredList = if (currentSearchQuery.isBlank()) {
            originalBirthdayList
        } else {
            originalBirthdayList.filter {
                (it.name?.contains(currentSearchQuery, ignoreCase = true) == true) ||
                (it.remarks?.contains(currentSearchQuery, ignoreCase = true) == true)
            }
        }

        filteredList = when (currentSortBy) {
            SortBy.NAME -> filteredList.sortedBy { it.name?.lowercase() ?: "" }
            SortBy.AGE -> filteredList.sortedBy { it.age }
            SortBy.BIRTHDAY -> filteredList.sortedWith(compareBy({ it.birthMonth }, { it.birthDayOfMonth }))
            SortBy.NONE -> filteredList
        }

        _birthdayUIState.update { uiState ->
            uiState.copy(isLoading = false, birthdays = filteredList)
        }
    }

    fun deleteBirthday(birthdayId: Int, userId: String) {
        _singleBirthdayUIState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = BirthdayRepository.deleteBirthday( birthdayId )) {
                is NetworkResult.Success -> {
                    _singleBirthdayUIState.update {
                        it.copy(isLoading = false, birthday = result.data)
                    }
                    getBirthdays(userId = userId)
                }

                is NetworkResult.Error -> {
                    _birthdayUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun addBirthday(birthday: Birthday) {
        _singleBirthdayUIState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = BirthdayRepository.addBirthday(birthday)) {
                is NetworkResult.Success -> {
                    _singleBirthdayUIState.update {
                        it.copy(isLoading = false, birthday = result.data)
                    }
                    getBirthdays(userId = birthday.userId)
                }

                is NetworkResult.Error -> {
                    _birthdayUIState.update {
                        it.copy(isLoading = false, error = result.error)
                    }
                }
            }
        }
    }

    fun updateBirthday(birthdayId: Int, birthday: Birthday) {
        _singleBirthdayUIState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = BirthdayRepository.updateBirthday(birthdayId, birthday)) {
                is NetworkResult.Success -> {
                    _singleBirthdayUIState.update {
                        it.copy(isLoading = false, birthday = result.data)
                    }
                    getBirthdays(userId = birthday.userId)
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
