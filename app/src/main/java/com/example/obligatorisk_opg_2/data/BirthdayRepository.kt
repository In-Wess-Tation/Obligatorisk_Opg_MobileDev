package com.example.obligatorisk_opg_2.data

interface BirthdayRepository {
    suspend fun getBirthdays(): NetworkResult<List<Birthday>>

}