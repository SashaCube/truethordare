package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.info

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R

class InfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

    }

    fun exit(view: View) {
        finish()
    }

}
