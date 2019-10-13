package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player

interface IPlayerView {

    fun showDialogAddPlayer()

    fun showDialogMaxPlayersCount()

    fun showDialogDeletePlayer(player: Player)

    fun dismissDialogAddPlayer()

    fun dismissDialogDeletePlayer()
}