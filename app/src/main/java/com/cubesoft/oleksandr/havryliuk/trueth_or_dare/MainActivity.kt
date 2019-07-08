package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.EditPlayersActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.GameView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers.PlayersManager
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PlayersManager(mutableListOf())

        gameView = find(R.id.game_view)
    }

    override fun onStart() {
        super.onStart()
        Log.i("main", "start")
        gameView.setPlayers(PlayersManager.instance().getAllNames())

    }

    fun editPlayers(view: View) = startActivity(Intent(this, EditPlayersActivity::class.java))
}
