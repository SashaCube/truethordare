package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player

interface IPlayerViewModel {

    fun onDeleteClick(player: Player)

    fun onAddClick()

    fun onDialogAddPlayerName(name: String)

    fun onDialogAddPlayerCancel()

    fun onDialogDeleteCancel(player: Player)

    fun onDialogDelete(player: Player)
}