package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.EditPlayersActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.GameView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers.PlayersManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import org.jetbrains.anko.alert
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PlayersManager()

        gameView = find(R.id.game_view)
    }

    override fun onStart() {
        super.onStart()
        Log.i("main", "start")
        gameView.setPlayers(PlayersManager.instance().getAllNames())

    }

    fun editPlayers(view: View) = startActivity(Intent(this, EditPlayersActivity::class.java))

    fun onSpin(view: View) {
        val player = PlayersManager.instance().getRandomPlayer() ?: Player("")
        alert(
            getString(R.string.truth_or_dire),
            getString(R.string.player_choose, player.name)
        ) {
            positiveButton(R.string.action) { actionAlert(player) }
            negativeButton(R.string.truth) { truthAlert(player) }
        }.show()
    }

    fun actionAlert(player: Player) {
        val action = getRandomAction()
        alert(action, player.name) {
            positiveButton(R.string.done) { }
        }.show()
    }

    fun truthAlert(player: Player) {
        val truth = getRandomQuestion()
        alert(truth, player.name) {
            positiveButton(R.string.done) { }
        }.show()
    }

    fun getRandomQuestion(): String {
        return "Якби тобі запропонували потрапити на обкладинку будь-якого журналу, то яку б ти вибрав?"
    }

    fun getRandomAction(): String {
        return "Встань з-за столу і голосно занявкали, як кіт. Або загавкали, як собака."
    }
}
