package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.injection.module

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.ContentRepository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.IContentRepository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.IPlayerRepository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.PlayerRepository
import dagger.Binds
import dagger.Module

/**
 * Module that provides all dependencies from the data package/layer.
 */
@Module
abstract class DataModule {

    @Binds
    abstract fun bindContentRepository(contentRepository: ContentRepository): IContentRepository

    @Binds
    abstract fun bindPlayerRepository(playerRepository: PlayerRepository): IPlayerRepository
}