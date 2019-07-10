package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player

interface EditPlayersContract {
    interface IEditPlayersView {

        fun showMessage(message: String)

        fun setPlayers(players: List<Player>)
    }

    interface IEditPlayersPresenter {

        fun deletePlayer(name: String)

        fun addPlayer(name: String)
    }
}