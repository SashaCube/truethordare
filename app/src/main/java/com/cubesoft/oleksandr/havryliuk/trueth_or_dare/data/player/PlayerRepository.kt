package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util.PLAYERS
import javax.inject.Inject

class PlayerRepository @Inject constructor(context: Context) : IPlayerRepository {

    private var players: MutableLiveData<List<Player>>

    init {
        with(SharedPreferenceHelper) {
            init(context)
            players = MutableLiveData()
            players.value = getStringList(PLAYERS).map { Player(it) }
        }
    }

    override fun getPlayers() = players

    override fun addPlayer(player: Player) {
        val newPlayerList = mutableListOf<Player>()
        players.value?.let { newPlayerList.addAll(it) }
        newPlayerList.add(player)
        players.value = newPlayerList
    }

    override fun deletePlayer(player: Player) {
        val newPlayerList = mutableListOf<Player>()
        players.value?.let { newPlayerList.addAll(it) }
        newPlayerList.remove(player)
        players.value = newPlayerList
    }

    override fun savePlayers() {
        players.value?.let { SharedPreferenceHelper.setStringList(PLAYERS, it.map { it.name }) }
    }
}