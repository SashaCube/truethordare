package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util.PLAYERS
import javax.inject.Inject

class PlayerRepository @Inject constructor(context: Context) : IPlayerRepository {

    private var players: MutableLiveData<List<String>>

    init {
        with(SharedPreferenceHelper) {
            init(context)
            players = MutableLiveData()
            players.value = getStringList(PLAYERS)
        }
    }

    override fun getPlayers() = players

    override fun addPlayer(playerName: String) {
        val newPlayerList = mutableListOf<String>()
        players.value?.let { newPlayerList.addAll(it) }
        newPlayerList.add(playerName)
        players.value = newPlayerList
    }

    override fun deletePlayer(playerName: String) {
        val newPlayerList = mutableListOf<String>()
        players.value?.let { newPlayerList.addAll(it) }
        newPlayerList.remove(playerName)
        players.value = newPlayerList
    }

    override fun savePlayers() {
        players.value?.let { SharedPreferenceHelper.setStringList(PLAYERS, it) }
    }
}