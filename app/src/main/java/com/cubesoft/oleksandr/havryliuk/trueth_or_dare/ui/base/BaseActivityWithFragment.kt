package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentFactory
import dagger.android.AndroidInjection

abstract class BaseActivityWithFragment : AppCompatActivity() {

    abstract val supportFragmentFactory: FragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        supportFragmentManager.fragmentFactory = supportFragmentFactory
        super.onCreate(savedInstanceState)
    }
}