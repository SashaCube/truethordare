package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Module that provide context to whole app.
 */
@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindContext(application: Application): Context
}
