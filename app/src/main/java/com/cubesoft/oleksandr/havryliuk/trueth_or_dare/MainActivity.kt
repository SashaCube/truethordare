package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.EditPlayersActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.GameView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.GameDatabase
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Action
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Question
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import java.util.*

class MainActivity : AppCompatActivity() {

    private var mDb: GameDatabase? = null

    private lateinit var mGameView: GameView

    private lateinit var mDbWorkerThread: DbWorkerThread

    private val mUiHandler = Handler()

    private lateinit var actions: List<Action>
    private lateinit var questions: List<Question>
    private lateinit var players: List<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread")
        mDbWorkerThread.start()

        mDb = GameDatabase.getInstance(this)

        mGameView = find(R.id.game_view)

        fetchDataFromDb()
    }

    override fun onStart() {
        super.onStart()
        fetchPlayersFromDb()
    }

    fun editPlayers(view: View) = startActivity(Intent(this, EditPlayersActivity::class.java))

    fun onSpin(view: View) {
        val player = players.getRandom()
        alert(
            getString(R.string.truth_or_dire),getString(R.string.player_choose, player.name)
        ) {
            positiveButton(R.string.action) { actionAlert(player) }
            negativeButton(R.string.truth) { truthAlert(player) }
        }.show()
    }

    fun actionAlert(player: Player) {
        alert(actions.getRandom().action, player.name) {
            positiveButton(R.string.done) { }
        }.show()
    }

    fun truthAlert(player: Player) {
        alert(questions.getRandom().question, player.name) {
            positiveButton(R.string.done) { }
        }.show()
    }

    override fun onDestroy() {
        GameDatabase.destroyInstance()
        mDbWorkerThread.quit()
        super.onDestroy()
    }

    fun fetchPlayersFromDb() {
        val task = Runnable {
            val players = mDb?.playerDao()?.all
            mUiHandler.post {
                if (players == null) {
                    //addPlayer
                } else {
                    updateDate(players = players)
                    bindDataWithUI()
                }
            }
        }
        mDbWorkerThread.postTask(task)
    }

    fun fetchDataFromDb() {
        val task = Runnable {
            val actions = mDb?.actionDao()?.all
            val questions = mDb?.questionDao()?.all
            mUiHandler.post {
                if (questions == null || actions == null) {
                    //addPlayer
                } else {
                    updateDate(actions = actions, questions = questions)
                }
            }
        }
        mDbWorkerThread.postTask(task)
    }

    fun bindDataWithUI() {
        mGameView.setPlayers(players.getAllNames())
    }

    //extension fun
    fun List<Player>.getAllNames(): List<String> {
        var list = mutableListOf<String>()
        for (p in this) {
            list.add(p.name)
        }

        return list
    }

    //extension fun
    fun List<Question>.getRandom(): Question = questions[Random().nextInt(questions.size)]

    //extension fun
    fun List<Action>.getRandom(): Action = actions[Random().nextInt(actions.size)]

    fun List<Player>.getRandom(): Player = players[Random().nextInt(players.size)]

    private fun updateDate(
        players: List<Player>? = null,
        actions: List<Action>? = null,
        questions: List<Question>? = null
    ) {
        players?.let { this.players = players }
        actions?.let { this.actions = actions }
        questions?.let { this.questions = questions }
    }
}
