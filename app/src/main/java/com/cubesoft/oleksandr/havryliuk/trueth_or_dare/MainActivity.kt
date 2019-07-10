package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.EditPlayersActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.GameView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.DbWorkerThread
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.GameDatabase
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Action
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Question
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.populateActions
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.populateQuestions
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {

    private var mDb: GameDatabase? = null

    private lateinit var mGameView: GameView
    private lateinit var mDbWorkerThread: DbWorkerThread

    private val mUiHandler = Handler()

    private var actions: List<Action> = listOf()
    private var questions: List<Question> = listOf()
    private var players: List<Player> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread_main")
        mDbWorkerThread.start()

        mDb = GameDatabase.getInstance(this)

        mGameView = find(R.id.game_view)

        fetchDataFromDb()
    }

    private fun fetchPlayersFromDb() {
        val task = Runnable {
            val players = mDb?.playerDao()?.all
            mUiHandler.post {
                if (players == null || players.isEmpty()) {
                    Log.i("FetchPlayersFromDb_main", "not players")
                } else {
                    Log.i("FetchPlayersFromDb_main", "fetch ${players.size} players")
                    updateDate(players = players)
                    bindDataWithUI()
                }
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun fetchDataFromDb() {
        val task = Runnable {
            var actions = mDb?.actionDao()?.all
            var questions = mDb?.questionDao()?.all

            if (actions == null || actions.isEmpty()) {
                Log.i("FetchDataFromDb_main", "no actions -> populate database")
                mDb?.actionDao()?.let {
                    populateActions(it, resources.getStringArray(R.array.default_actions).asList())
                }
                actions = mDb?.actionDao()?.all
            }

            if (questions == null || questions.isEmpty()) {
                Log.i("FetchDataFromDb_main", "no questions -> populate database")
                mDb?.questionDao()?.let {
                    populateQuestions(it, resources.getStringArray(R.array.default_questions).asList())
                }
                questions = mDb?.questionDao()?.all
            }

            Log.i("FetchDataFromDb_main", "update: ${actions?.size} -> actions; ${questions?.size} -> questions")
            mUiHandler.post {
                updateDate(actions = actions, questions = questions)
            }
        }
        mDbWorkerThread.postTask(task)
    }


    private fun bindDataWithUI() {
        Log.i("BindData_main", "bind players(${players.size}) to view")
        mGameView.setPlayers(players.getAllNames())
    }

    private fun updateDate(
        players: List<Player>? = null,
        actions: List<Action>? = null,
        questions: List<Question>? = null
    ) {
        Log.i("UpdateData_main", "update data")
        players?.let { this.players = players }
        actions?.let { this.actions = actions }
        questions?.let { this.questions = questions }
    }

    fun editPlayers(view: View) = startActivity(Intent(this, EditPlayersActivity::class.java))

    fun onSpin(view: View) {

        if (players.isEmpty()) {
            alert(R.string.add_one_player_to_start) {
                yesButton { }
            }.show()
        } else {
            val player = players.getRandom()!!

            alert(
                getString(R.string.truth_or_dire), getString(R.string.player_choose, player.name)
            ) {
                positiveButton(R.string.action) { actionAlert(player) }
                negativeButton(R.string.truth) { truthAlert(player) }
            }.show()

        }
    }

    private fun actionAlert(player: Player) {
        actions.getRandom()?.action?.let {
            alert(it, player.name) {
                positiveButton(R.string.done) { }
            }.show()
        }
    }

    private fun truthAlert(player: Player) {
        questions.getRandom()?.question?.let {
            alert(it, player.name) {
                positiveButton(R.string.done) { }
            }.show()
        }
    }

    override fun onStart() {
        super.onStart()
        fetchPlayersFromDb()
    }

    override fun onDestroy() {
        mDbWorkerThread.quit()
        super.onDestroy()
    }
}
