package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player

import androidx.lifecycle.LiveData

interface IPlayerRepository {

    fun getPlayers(): LiveData<List<String>>

    fun addPlayer(playerName: String)

    fun deletePlayer(playerName: String)

    fun savePlayers()
}