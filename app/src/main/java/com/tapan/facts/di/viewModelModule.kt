package com.tapan.facts.di

import com.tapan.facts.data.repository.FactRepository
import com.tapan.facts.data.repository.FactRepositoryImpl
import com.tapan.facts.presentation.fact.FactViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Injecting repositories
 */
val viewModelModule = module {
    viewModel { FactViewModel() }
}