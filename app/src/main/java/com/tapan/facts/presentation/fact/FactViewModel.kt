package com.tapan.facts.presentation.fact

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tapan.facts.data.models.FactsRS
import com.tapan.facts.data.repository.FactRepository
import com.tapan.facts.presentation.core.BaseViewModel
import org.koin.core.inject

open class FactViewModel : BaseViewModel() {

    var firstVisibleItem: Int = 0
    private val factRepo by inject<FactRepository>()
    @VisibleForTesting
    private val factsLiveData by lazy { MutableLiveData<FactsRS>() }

    fun getFacts() {
        //To work with viewModel scope
        postData(factsLiveData) {
            factRepo.getFacts()
        }
    }

    fun getFactsLiveData(): LiveData<FactsRS> =
        factsLiveData // to provide InMutable liveData to views
}