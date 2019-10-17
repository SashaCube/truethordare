package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.player

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.IPlayerRepository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val playerRepository: IPlayerRepository
) : ViewModel(), IPlayerViewModel {

    override val players = playerRepository.getPlayers()

    override val addAlarmDialogState = MutableLiveData<Boolean>()

    override val deleteAlarmDialogState = MutableLiveData<Pair<Boolean, Player>>()

    override val maxUsersAlarmDialogState = MutableLiveData<Boolean>()

    override fun onDeleteClick(player: Player) {
        deleteAlarmDialogState.postValue(SHOW to player)
    }

    override fun onAddClick() {
        addAlarmDialogState.postValue(SHOW)
    }

    override fun onDialogAddPlayerName(name: String) {
        addAlarmDialogState.postValue(DISMISS)
        if (players.value?.size ?: 0 < MAX_COUNT_OF_PLAYERS) {
            playerRepository.addPlayer(Player(name))
        } else {
            maxUsersAlarmDialogState.postValue(SHOW)
        }
    }

    override fun onDialogAddPlayerCancel() {
        addAlarmDialogState.postValue(DISMISS)
    }

    override fun onDialogDeleteCancel(player: Player) {
        deleteAlarmDialogState.postValue(DISMISS to player)
    }

    override fun onDialogDelete(player: Player) {
        playerRepository.deletePlayer(player)
        deleteAlarmDialogState.postValue(DISMISS to player)
    }

    companion object {
        const val SHOW = true
        const val DISMISS = false
        const val MAX_COUNT_OF_PLAYERS = 10
    }
}