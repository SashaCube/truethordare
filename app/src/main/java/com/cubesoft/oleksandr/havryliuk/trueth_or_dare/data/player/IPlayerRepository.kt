package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player

import androidx.lifecycle.LiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player

interface IPlayerRepository {

    fun getPlayers(): LiveData<List<Player>>

    fun addPlayer(player: Player)

    fun deletePlayer(player: Player)

    fun savePlayers()
}