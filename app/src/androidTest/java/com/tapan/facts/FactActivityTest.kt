package com.tapan.facts

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.*
import com.tapan.facts.data.models.FactsRS
import com.tapan.facts.data.repository.FactRemoteSource
import com.tapan.facts.data.repository.FactRepository
import com.tapan.facts.presentation.fact.FactActivity
import com.tapan.facts.presentation.fact.FactViewModel
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.mock.declareMock
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock


@LargeTest
@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class FactActivityTest : AutoCloseKoinTest() {


    @get:Rule
    val activityRule = ActivityTestRule(FactActivity::class.java, true, true)

    @Test
    public fun testLoadData() {

        Thread.sleep(1500)
        Espresso.onView(ViewMatchers.withId(R.id.rvFacts))
            .check { view, noViewFoundException ->
                assert(view.visibility == View.VISIBLE)
                assert(getRVcount()>0)
            }

        Espresso.onView(ViewMatchers.withId(R.id.cvNodata))
            .check { view, noViewFoundException ->
                assert(view.visibility == View.GONE)
            }
    }

    private fun getRVcount(): Int {
        val recyclerView = activityRule.activity.findViewById(R.id.rvFacts) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }
}