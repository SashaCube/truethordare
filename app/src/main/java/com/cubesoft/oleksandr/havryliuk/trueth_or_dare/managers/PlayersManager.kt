package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import java.util.*

class PlayersManager(
    var players: MutableList<Player> = mutableListOf()
) {
    companion object {
        private var instance: PlayersManager? = null
        fun instance() = instance!!
    }

    init {
        instance = this
    }

    fun add(player: Player) {
        players.add(player)
    }

    fun add(name: String) {
        players.add(Player(name))
    }

    fun remove(player: Player?): Boolean {
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
        return players[Random().nextInt(players.size)]
    }

    fun getAllPlayers(): List<Player> {
        if (players.isEmpty())
            players = mutableListOf()
        return players
    }

    fun getAllNames(): List<String> {
        var list = mutableListOf<String>()
        for (p in players) {
            list.add(p.name)
        }

        return list
    }

    private fun getPlayerByName(name: String): Player? {
        for (player in players)
            if (player.name == name) return player
        return null
    }
}