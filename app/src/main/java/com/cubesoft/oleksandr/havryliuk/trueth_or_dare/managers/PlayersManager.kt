package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import java.util.*

class PlayersManager(
    var players: MutableList<Player>
) {
    fun add(player: Player) {
        players.add(player)
    }

    fun add(name: String) {
        getPlayerByName(name)?.let { players.add(it) }
    }

    fun remove(player: Player): Boolean {
        return players.remove(player)
    }

    fun remove(name: String): Boolean {
        getPlayerByName(name)?.let { return players.remove(it) }
        return false
    }

    fun isAbleToAddPlayer(): Boolean {
        return players.size < 10
    }

    fun amountOfPlayers(): Int {
        return players.size
    }

    fun getRandomUser(): Player? {
        return if (players.isNotEmpty()) players[Random().nextInt(players.size)] else getEmptyUser()
    }

    fun getEmptyUser(): Player {
        return Player("player")
    }

    fun getAllPlayers(): List<Player>{
       if(players.isEmpty())
            players = mutableListOf(getEmptyUser())
        return players
    }

    private fun getPlayerByName(name: String): Player? {
        for (player in players)
            if (player.name == name) return player
        return null
    }
}