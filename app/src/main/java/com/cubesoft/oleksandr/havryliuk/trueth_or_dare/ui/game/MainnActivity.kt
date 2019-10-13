package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.BaseActivityWithFragment
import kotlinx.android.synthetic.main._navigation_layout.*
import javax.inject.Inject


class MainnActivity : BaseActivityWithFragment() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override val supportFragmentFactory: FragmentFactory
        get() = fragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._main_activity)
    }
}