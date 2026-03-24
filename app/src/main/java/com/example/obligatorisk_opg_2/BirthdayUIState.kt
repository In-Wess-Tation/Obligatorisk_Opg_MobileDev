package com.example.obligatorisk_opg_2

import com.example.obligatorisk_opg_2.data.Birthday

data class BirthdayUIState (
    val isLoading: Boolean = false,
    val birthdays: List<Birthday> = emptyList(),
    val error: String? = null
)