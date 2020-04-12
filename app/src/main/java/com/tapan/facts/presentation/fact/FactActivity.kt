package com.tapan.facts.presentation.fact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapan.facts.R
import com.tapan.facts.observe
import com.tapan.facts.presentation.core.BaseActivity
import com.tapan.facts.presentation.core.BaseViewModel
import com.tapan.facts.presentation.fact.adapter.FactAdapter
import kotlinx.android.synthetic.main.activity_fact.*

class FactActivity : BaseActivity<FactViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact)

        setupSwipeRefresh()
        setupAdapter()

        getViewModel().getFacts()
    }

    private fun setupAdapter() {
        rvFacts?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = FactAdapter()
        }
    }


    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener {
            getViewModel().getFacts()
        }
    }

    override fun attachLiveData() {
        observe(getViewModel().progressLiveData) {
            setProgressAndError(it)
        }

        observe(getViewModel().errorLiveData) {
            if (rvFacts.adapter?.itemCount == 0)
                cvNodata.visibility = View.VISIBLE
        }
        observe(getViewModel().getFactsLiveData()) {
            it?.apply {
                (rvFacts.adapter as? FactAdapter)?.apply {
                    val filterList = facts.filter {
                        !(it.description == null &&
                                it.title == null &&
                                it.imageHref == null)

                    }
                    setList(filterList)
                }
            }
        }
    }

    private fun setProgressAndError(it: Boolean?) {
        if (it == true) {
            swipeRefresh.isRefreshing = true
            cvNodata.visibility = View.GONE
        } else {
            swipeRefresh.isRefreshing = false
        }
    }
}
