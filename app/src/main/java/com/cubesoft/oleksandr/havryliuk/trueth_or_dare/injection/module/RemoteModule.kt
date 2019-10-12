package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module

import dagger.Module

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
//    @Module
//    companion object {
//        @Provides
//        @JvmStatic
//        fun provideApiService(): ApiService {
//            return ApiServiceFactory.makeApiService()
//        }
//    }
//
//    @Binds
//    abstract fun bindDeviceStore(deviceStore: DeviceRemote): IDeviceDataStore
//
//    @Binds
//    abstract fun bindUserStore(userStore: UserRemote): IUserDataStore
//
//    @Binds
//    abstract fun bindRoomStore(roomStore: RoomRemote): IRoomDataStore
//
//    @Binds
//    abstract fun bindBeaconStore(beaconStore: BeaconRemote): IBeaconDataStore
//
//    @Binds
//    abstract fun bindAuthservice(authService: AuthService): IAuthService
}