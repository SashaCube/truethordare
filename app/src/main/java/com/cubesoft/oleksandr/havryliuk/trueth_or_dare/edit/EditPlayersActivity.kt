package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.adapter.PlayersAdapter
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.dialog.AddPlayerDialog
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.dialog.DeletePlayerDialog
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.DbWorkerThread
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.GameDatabase
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import org.jetbrains.anko.*

class EditPlayersActivity : AppCompatActivity(), EditPlayersContract.IEditPlayersView,
    PlayersAdapter.OnItemClickListener {

    private val MAX_PLAYERS: Int = 10

    private var players: List<Player>? = null
    private lateinit var mDbWorkerThread: DbWorkerThread
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlayersAdapter

    override fun onItemClickListener(v: View, pos: Int) {
        players?.get(pos)?.name?.let { displaDeletePlayerDialog(v, it) }
    }

    lateinit var presenter: EditPlayersContract.IEditPlayersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_players)

        mDbWorkerThread = DbWorkerThread("dbWorkerThread_edit")
        mDbWorkerThread.start()

        initView()
        presenter = EditPlayersPresenter(
            this, GameDatabase.getInstance(this),
            mDbWorkerThread
        )
    }

    private fun initView() {
        recyclerView = find(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = PlayersAdapter()
        adapter.setOnItemClickListener(this)

        recyclerView.adapter = adapter
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun setPlayers(players: List<Player>) {
        this.players = players
        adapter.setData(players)
    }

    fun displayAddPlayerDialog(view: View) {

        if (players?.size!! >= MAX_PLAYERS)
            alert(R.string.max_amount_of_player) {
                yesButton { }
            }.show()
        else {

            val addPlayerDialog by lazy {
                contentView?.let {
                    AddPlayerDialog(AnkoContext.create(ctx, it))
                }
            }

            addPlayerDialog?.okButton?.setOnClickListener {
                val name = addPlayerDialog!!.newPlayerName.text.toString()

                if (name != "")
                    presenter.addPlayer(name)

                addPlayerDialog!!.dialog.dismiss()
            }

            addPlayerDialog?.cancelButton?.setOnClickListener {
                addPlayerDialog!!.dialog.dismiss()
            }
        }
    }

    fun displaDeletePlayerDialog(view: View, playerName: String) {
        val deletePlayerDialog by lazy {
            contentView?.let {
                DeletePlayerDialog(
                    AnkoContext.create(
                        ctx,
                        it
                    ), playerName
                )
            }
        }

        deletePlayerDialog?.okButton?.setOnClickListener {
            presenter.deletePlayer(playerName)
            deletePlayerDialog!!.dialog.dismiss()
        }

        deletePlayerDialog?.cancelButton?.setOnClickListener {
            deletePlayerDialog!!.dialog.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDbWorkerThread.quit()
    }

    fun exit(view: View) = finish()
}