package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentFactory
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        LocalModule::class,
        RemoteModule::class,
        DataModule::class,
        PresentationModule::class,
        UiModule::class
    ]
)
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun fragmentFactory(): FragmentFactory

    fun context(): Context
}
