package com.tapan.facts.presentation.core

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tapan.facts.R
import com.tapan.facts.data.core.MyException
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {
    abstract fun getViewModel(): T


    abstract fun attachLiveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
    }


    override fun onResume() {
        super.onResume()



        getViewModel().errorLiveData.observe(this, Observer {
            getViewModel().changeProgress(false)
            it?.apply {
                if (it is MyException) {
                    when (it) {
                        is MyException.UnKnownError -> {
                            Toast.makeText(
                                this@BaseActivity,
                                "Something went wrong",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                        is MyException.NetworkErrorError -> {
                            Toast.makeText(
                                this@BaseActivity,
                                getString(R.string.connection_error),
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }

                    }


                } else {
                    Log.e("Error", it.localizedMessage, it)
                }
                getViewModel().resetErrorLiveData()
            }
        })
        attachLiveData()
    }


}