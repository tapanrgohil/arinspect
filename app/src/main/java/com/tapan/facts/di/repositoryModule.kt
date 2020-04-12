package com.tapan.facts.di

import com.tapan.facts.data.repository.FactRemoteSource
import com.tapan.facts.data.repository.FactRemoteSourceImpl
import com.tapan.facts.data.repository.FactRepository
import com.tapan.facts.data.repository.FactRepositoryImpl
import org.koin.dsl.module


/**
 * Injecting repositories
 */
val repositorySourceModule = module {
    single<FactRepository> { FactRepositoryImpl(get()) }
    single<FactRemoteSource> { FactRemoteSourceImpl(get()) }
}