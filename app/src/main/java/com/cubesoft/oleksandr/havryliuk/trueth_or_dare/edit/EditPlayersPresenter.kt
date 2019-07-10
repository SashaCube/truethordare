package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit

import android.os.Handler
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.DbWorkerThread
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.GameDatabase
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player

class EditPlayersPresenter(
    val view: EditPlayersContract.IEditPlayersView,
    val mDb: GameDatabase
) : EditPlayersContract.IEditPlayersPresenter {

    private lateinit var mDbWorkerThread: DbWorkerThread
    private val mUiHandler = Handler()

    init {
        update()
    }

    override fun deletePlayer(name: String) {
        val task = Runnable {
            mDb.playerDao().deleteByName(name)
            mUiHandler.post { update() }
        }
        mDbWorkerThread.postTask(task)
    }

    override fun addPlayer(name: String) {
        val task = Runnable {
            mDb.playerDao().insert(Player(null, name))
            mUiHandler.post { update() }
        }
        mDbWorkerThread.postTask(task)
    }

    fun update() {
        val task = Runnable {
            val players = mDb?.playerDao()?.all
            mUiHandler.post {
                if (players == null) {
                    //TODO: display problem
                } else {
                    view.setPlayers(players)
                }
            }
        }
        mDbWorkerThread.postTask(task)
    }

    override fun onDestroy() {
        mDbWorkerThread.quit()
    }
}