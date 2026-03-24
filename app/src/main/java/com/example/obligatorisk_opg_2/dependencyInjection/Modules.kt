package com.example.obligatorisk_opg_2.dependencyInjection

import com.example.obligatorisk_opg_2.data.BirthdayRepository
import com.example.obligatorisk_opg_2.data.BirthdayRepositoryImpl
import com.example.obligatorisk_opg_2.data.BirthdaysAPI
import com.example.obligatorisk_opg_2.viewmodel.BirthdayViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {
    single <BirthdayRepository> { BirthdayRepositoryImpl(get(), get()) }
    single { Dispatchers.IO }
    single { BirthdayViewModel(get()) }
    single {
        Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://birthdaysrest.azurewebsites.net/api/Persons/")
            .build()
    }
    single { get<Retrofit>().create(BirthdaysAPI::class.java) }
}