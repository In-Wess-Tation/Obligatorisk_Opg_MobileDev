package com.example.obligatorisk_opg_2

import android.app.Application
import com.example.obligatorisk_opg_2.dependencyInjection.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BirthdayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BirthdayApplication)
            modules(appModules)
        }
    }
}