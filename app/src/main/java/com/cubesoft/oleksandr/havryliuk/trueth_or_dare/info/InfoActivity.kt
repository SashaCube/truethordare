package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.info

import android.app.Activity
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.log
import com.google.firebase.analytics.FirebaseAnalytics


class InfoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        findViewById<ImageView>(R.id.back_button_info).setOnClickListener {
            FirebaseAnalytics.getInstance(this).log(
                "onBackClick",
                "From InfoActivity to MainActivity"
            )
            finish()
        }

        val policy = Html.fromHtml(getString(R.string.privacy_policy))
        val termsOfUse = findViewById<TextView>(R.id.link_pp)
        termsOfUse.text = policy
        termsOfUse.movementMethod = LinkMovementMethod.getInstance()
    }
}
