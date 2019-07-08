package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.EditPlayersActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, EditPlayersActivity::class.java))
    }
}
