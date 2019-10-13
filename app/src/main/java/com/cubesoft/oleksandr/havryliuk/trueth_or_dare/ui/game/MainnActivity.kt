package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import kotlinx.android.synthetic.main._navigation_layout.*


class MainnActivity : AppCompatActivity() {

    private val navController by lazy { (nav_host_fragment as NavHostFragment).navController }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_activity)
    }
}