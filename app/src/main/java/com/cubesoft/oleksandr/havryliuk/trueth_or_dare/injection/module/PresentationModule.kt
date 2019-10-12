package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module

import androidx.lifecycle.ViewModelProvider
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * Module that provides all dependencies from the presentation package/layer.
 */
@Module
abstract class PresentationModule {
    //
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
//
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}