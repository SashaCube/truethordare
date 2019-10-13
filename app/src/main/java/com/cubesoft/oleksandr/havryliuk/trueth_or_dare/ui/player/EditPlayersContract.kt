package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player

interface EditPlayersContract {
    interface IEditPlayersView {

        fun showMessage(message: String)

        fun setPlayers(players: List<String>)
    }

    interface IEditPlayersPresenter {

        fun deletePlayer(name: String)

        fun addPlayer(name: String)
    }
}