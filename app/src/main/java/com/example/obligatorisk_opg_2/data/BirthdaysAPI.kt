package com.example.obligatorisk_opg_2.data


import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query


interface BirthdaysAPI {
    @GET(".")
    suspend fun getBirthdays(): Response<List<Birthday>>

    @GET(".")
    suspend fun getBirthdays(@Query("user_id") userId: String): Response<List<Birthday>>

    @POST(".")
    suspend fun addBirthday(@Body birthday: Birthday): Response<Birthday>

    @DELETE("{id}")
    suspend fun deleteBirthday(@Path("id") id: Int): Response<Birthday>

    @PUT("{id}")
    suspend fun updateBirthday(@Path("id") id: Int, @Body birthday: Birthday): Response<Birthday>
}