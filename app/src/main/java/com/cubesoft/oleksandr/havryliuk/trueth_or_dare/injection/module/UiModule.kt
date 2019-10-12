package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module

import androidx.fragment.app.FragmentFactory
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.factory.CustomFragmentFactory
import dagger.Binds
import dagger.Module

/**
 * Module that provides all dependencies from the mobile-ui package/layer and injects all activities.
 */
@Module
abstract class UiModule {

    //    @ContributesAndroidInjector()
//    abstract fun contributeForgotPasswordActivityAndroidInjector(): ForgotPasswordActivity
//
//    @Binds
//    @IntoMap
//    @FragmentKey(RecoverPasswordFragment::class)
//    abstract fun bindRecoverPasswordFragment(fragment: RecoverPasswordFragment): Fragment
//
    @Binds
    abstract fun bindFragmentFactory(factory: CustomFragmentFactory): FragmentFactory
}