package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers.PlayersManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.Repository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player

class EditPlayersPresenter(
    val view: EditPlayersContract.IEditPlayersView,
    val repository: Repository,
    val playersManager: PlayersManager
) : EditPlayersContract.IEditPlayersPresenter {
    init {
        update()
    }

    override fun deletePlayer(player: Player?) {
        playersManager.remove(player)
        update()
    }

    override fun addPlayer(player: Player) {
        playersManager.add(player)
        update()
    }

    fun update() {
        view.setPlayers(playersManager.getAllPlayers())
    }
}