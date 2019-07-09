package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.EditPlayersActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.GameView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers.GameManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers.PlayersManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import org.jetbrains.anko.alert
import org.jetbrains.anko.find

class MainActivity : AppCompatActivity() {

    lateinit var gameView: GameView
    lateinit var gameManager: GameManager
    lateinit var playersManager: PlayersManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GameManager()
        PlayersManager()

        playersManager = PlayersManager.instance()
        gameManager = GameManager.instance()

        gameView = find(R.id.game_view)
    }

    override fun onStart() {
        super.onStart()
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
        val action = gameManager.getRandomAction()
        alert(action, player.name) {
            positiveButton(R.string.done) { }
        }.show()
    }

    fun truthAlert(player: Player) {
        val truth = gameManager.getRandomQuestion()
        alert(truth, player.name) {
            positiveButton(R.string.done) { }
        }.show()
    }
}
