package com.tapan.facts

import android.view.View
import android.widget.ImageView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide


fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, androidx.lifecycle.Observer(body))

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.visible(visible: Boolean) {
    if (visible) {
        visible()
    } else {
        gone()
    }
}

fun ImageView.loadImage(filePath: String, placeHolder: Int? = null) {

    val requestBuilder = Glide.with(this).load(filePath)
    if (placeHolder != null)
        requestBuilder.placeholder(placeHolder)
    else {
        requestBuilder.placeholder(R.drawable.place_holder)
    }
    if (placeHolder != null)
        requestBuilder.error(placeHolder)
    else {
        requestBuilder.error(R.drawable.place_holder)
    }
    requestBuilder.into(this)
}
