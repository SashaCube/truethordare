package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.factory.CustomFragmentFactory
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.key.FragmentKey
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game.GameFragment
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game.MainActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game.MainnActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.info.InfoFragment
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player.PlayerFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

/**
 * Module that provides all dependencies from the mobile-ui package/layer and injects all activities.
 */
@Module
abstract class UiModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivityAndroidInjector(): MainnActivity

    @Binds
    @IntoMap
    @FragmentKey(PlayerFragment::class)
    abstract fun bindPlayerFragment(playerFragment: PlayerFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(InfoFragment::class)
    abstract fun bindInfoFragment(infoFragment: InfoFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(GameFragment::class)
    abstract fun bindGameFragment(gameFragment: GameFragment): Fragment

    @Binds
    abstract fun bindFragmentFactory(factory: CustomFragmentFactory): FragmentFactory
}