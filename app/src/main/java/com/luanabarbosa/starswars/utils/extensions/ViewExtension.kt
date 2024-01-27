package com.luanabarbosa.starswars.utils.extensions

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.io.Serializable

fun TextView.setValueOrDefault(value: String?, defaultText: String): TextView {
    text = value ?: defaultText
    return this
}

fun ImageView.load(url: String?) {
    Picasso.get()
        .load(url)
        .into(this)
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

inline fun <reified T : Serializable> Bundle.serializable(key: String): T? = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> getSerializable(key, T::class.java)
    else -> @Suppress("DEPRECATION") getSerializable(key) as? T
}
