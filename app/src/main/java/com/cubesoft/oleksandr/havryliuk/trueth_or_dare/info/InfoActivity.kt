package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.info

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R

class InfoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        findViewById<ImageView>(R.id.back_button_info).setOnClickListener { finish() }
    }
}
