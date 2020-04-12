package com.tapan.facts

import android.app.Application
import com.tapan.facts.di.networkModule
import com.tapan.facts.di.repositorySourceModule
import com.tapan.facts.di.viewModelModule
import org.koin.core.context.startKoin

class FactsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(arrayListOf(networkModule, repositorySourceModule, viewModelModule))
        }
    }
}