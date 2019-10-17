package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.factory.ViewModelFactory
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.key.ViewModelKey
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.player.PlayerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Module that provides all dependencies from the presentation package/layer.
 */
@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    abstract fun playerMainViewModel(playerViewModel: PlayerViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}