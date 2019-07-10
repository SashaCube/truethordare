package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit

import android.os.Handler
import android.util.Log
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.DbWorkerThread
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.GameDatabase
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player

class EditPlayersPresenter(
    val view: EditPlayersContract.IEditPlayersView,
    val mDb: GameDatabase,
    val mDbWorkerThread: DbWorkerThread
) : EditPlayersContract.IEditPlayersPresenter {

    private val mUiHandler = Handler()

    init {
        update()
    }

    override fun deletePlayer(name: String) {
        Log.i("deletePlayer_edit", "delete player -> $name")
        val task = Runnable {
            mDb.playerDao().deleteByName(name)
            mUiHandler.post { update() }
        }
        mDbWorkerThread.postTask(task)
    }

    override fun addPlayer(name: String) {
        Log.i("addPlayer_edit", "add player -> $name")
        val task = Runnable {
            mDb.playerDao().insert(Player(null, name))
            mUiHandler.post { update() }
        }
        mDbWorkerThread.postTask(task)
    }

    fun update() {
        val task = Runnable {
            val players = mDb.playerDao().all
            mUiHandler.post {
                view.setPlayers(players)
                Log.i("Update_edit", "update players(${players.size})")
            }
        }
        mDbWorkerThread.postTask(task)
    }
}