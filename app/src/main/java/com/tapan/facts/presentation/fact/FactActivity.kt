package com.tapan.facts.presentation.fact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tapan.facts.R
import com.tapan.facts.presentation.core.BaseActivity
import com.tapan.facts.presentation.core.BaseViewModel

class FactActivity : BaseActivity<FactViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact)
    }

    override fun attachLiveData() {

    }
}
