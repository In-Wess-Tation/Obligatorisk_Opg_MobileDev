package com.example.obligatorisk_opg_2.data

interface BirthdayRepository {
    suspend fun getBirthdays(): NetworkResult<List<Birthday>>
    suspend fun deleteBirthday(birthdayId: Int): NetworkResult<Birthday>
    suspend fun addBirthday(birthday: Birthday): NetworkResult<Birthday>
    suspend fun updateBirthday(birthdayId: Int, birthday: Birthday): NetworkResult<Birthday>
}