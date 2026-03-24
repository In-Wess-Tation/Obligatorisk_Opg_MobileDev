package com.example.obligatorisk_opg_2.data


import retrofit2.Response
import retrofit2.http.GET


interface BirthdaysAPI {
    @GET("birthdays")
    suspend fun getBirthdays(): Response<List<Birthday>>

//    @POST("birthdays")
//    suspend fun addBirthday(@Body birthday: Birthday): Response<Birthday>
//
//    @DELETE("birthdays/{id}")
//    suspend fun deleteBirthday(@Path("birthdayId") BirthdayId: String): Response<Unit>
//
//    @PUT("birthdays/{id}")
//    suspend fun updateBirthday(@Path("birthdayId") BirthdayId: String, @Body birthday: Birthday): Response<Birthday>



}