package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.util.*

class PlayersManager {

    companion object {
        private const val TAG = "PlayersManager"
        private const val PLAYERS = "Players"
        lateinit var sPref: SharedPreferences
        private lateinit var edit: SharedPreferences.Editor
        private var players: MutableList<String> = mutableListOf()
        private var randomPlayers: MutableList<String> = mutableListOf()
        const val MAX_PLAYERS: Int = 10

        fun init(context: Context) {
            sPref = android.preference.PreferenceManager.getDefaultSharedPreferences(context)
            edit = sPref.edit()
            val playersSet =
                getStringSet(PLAYERS)
            if (playersSet.isNotEmpty()) {
                players = playersSet.toMutableList()
            } else {
                players.clear()
            }
        }

        fun save() {
            setStringSet(
                PLAYERS,
                players.toSet()
            )
        }

        fun setStringSet(key: String, value: Set<String>) {
            Log.i(TAG, "setString $key=$value")
            edit.putStringSet(key, value)
            edit.commit()
        }

        fun getStringSet(key: String): MutableSet<String> =
            sPref.getStringSet(key, listOf<String>().toMutableSet()) as MutableSet<String>

        fun add(player: String) = players.add(player)

        fun remove(player: String) = players.remove(player)

        fun size() = players.size

        fun players(): List<String> {
            if (players.size > 0 && players[0] == "") {
                players.clear()
            }
            return players
        }

        fun getRandomPlayer(): String {
            if (randomPlayers.isEmpty()) {
                randomPlayers.addAll(players)
            }
            val nextPlayer = randomPlayers[Random().nextInt(randomPlayers.size)]
            randomPlayers.remove(nextPlayer)
            return nextPlayer
        }
    }
}