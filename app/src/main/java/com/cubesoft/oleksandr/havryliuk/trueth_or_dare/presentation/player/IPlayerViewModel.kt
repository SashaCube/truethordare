package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player

interface IPlayerViewModel {

    val players: LiveData<List<Player>>

    val addAlarmDialogState: MutableLiveData<Boolean>

    val deleteAlarmDialogState: MutableLiveData<Pair<Boolean, Player>>

    val maxUsersAlarmDialogState: MutableLiveData<Boolean>

    fun onDeleteClick(player: Player)

    fun onAddClick()

    fun onDialogAddPlayerName(name: String)

    fun onDialogAddPlayerCancel()

    fun onDialogDeleteCancel(player: Player)

    fun onDialogDelete(player: Player)
}