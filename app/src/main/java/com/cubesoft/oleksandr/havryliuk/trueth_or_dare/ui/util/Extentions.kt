package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import com.google.firebase.analytics.FirebaseAnalytics

fun FirebaseAnalytics.log(eventName: String, description: String = "") {
    val params = Bundle()
    params.putString("description", description)
    this.logEvent(eventName, params)
}

fun View.color(id: Int) = ContextCompat.getColor(context, id)

fun View.getHtmlText(id: Int): Spanned = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(resources.getString(id), HtmlCompat.FROM_HTML_MODE_LEGACY)
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(resources.getString(id))
}

fun View.getHtmlText(text: String) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    Html.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
} else {
    @Suppress("DEPRECATION")
    Html.fromHtml(text)
}