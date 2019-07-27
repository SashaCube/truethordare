package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit

import android.util.Log
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.PlayersManager

class EditPlayersPresenter(
    private val view: EditPlayersContract.IEditPlayersView
) : EditPlayersContract.IEditPlayersPresenter {
    init {
        update()
    }

    override fun deletePlayer(name: String) {
        PlayersManager.remove(name)
        update()
        Log.i("deletePlayer_edit", "delete player -> $name")
    }

    override fun addPlayer(name: String) {
        PlayersManager.add(name)
        update()
        Log.i("addPlayer_edit", "add player -> $name")
    }

    private fun update() {
        view.setPlayers(PlayersManager.players())
        Log.i("Update_edit", "update players(${PlayersManager.size()})")
    }
}