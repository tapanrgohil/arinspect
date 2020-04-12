package com.tapan.facts.presentation.fact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tapan.facts.data.models.FactsRS
import com.tapan.facts.data.repository.FactRepository
import com.tapan.facts.presentation.core.BaseViewModel
import org.koin.core.inject

class FactViewModel : BaseViewModel() {

    private val factRepo by inject<FactRepository>()
    private val factsLiveData by lazy { MutableLiveData<FactsRS>() }

    fun getFacts() {
        //To work with viewModel scope
        postData(factsLiveData) {
            factRepo.getFacts()
        }
    }

    fun getFactsLiveData() =
        (factsLiveData as LiveData<FactsRS>) // to provide InMutable liveData to views
}