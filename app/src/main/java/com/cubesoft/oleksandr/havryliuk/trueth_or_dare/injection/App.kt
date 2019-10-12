//package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection
//
//import android.app.Application
//
//
//class App : Application(), HasAndroidInjector {
//
//    @Inject
//    lateinit var androidInjector: DispatchingAndroidInjector<Any>
//
//    override fun androidInjector(): AndroidInjector<Any> {
//        return androidInjector
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        DaggerAppComponent.builder()
//            .application(this)
//            .build()
//            .inject(this)
//    }
//}
