package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

fun FirebaseAnalytics.log(eventName: String, description: String = "") {
    val params = Bundle()
    params.putString("description", description)
    this.logEvent(eventName, params)
}
