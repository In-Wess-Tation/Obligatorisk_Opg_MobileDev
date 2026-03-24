package com.example.obligatorisk_opg_2

import com.example.obligatorisk_opg_2.data.Birthday

data class SingleBirthdayUIState(
    val isLoading: Boolean = false,
    val birthday: Birthday? = null,
    val error: String? = null
)
