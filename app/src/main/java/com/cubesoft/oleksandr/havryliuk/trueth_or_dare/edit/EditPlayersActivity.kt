package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.adapter.PlayersAdapter
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.dialog.AddPlayerDialog
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.dialog.DeletePlayerDialog
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers.PlayersManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.Repository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import kotlinx.android.synthetic.main.activity_edit_players.*
import org.jetbrains.anko.*

class EditPlayersActivity : AppCompatActivity(), EditPlayersContract.IEditPlayersView,
    PlayersAdapter.OnItemClickListener {

    private var players: List<Player>? = null

    override fun onItemClickListener(v: View, pos: Int) {
        players?.get(pos)?.name?.let { displaDeletePlayerDialog(v, it) }
    }

    lateinit var presenter: EditPlayersContract.IEditPlayersPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_players)

        initView()
        presenter = EditPlayersPresenter(
            this, Repository(mutableListOf()),
            PlayersManager(mutableListOf())
        )
    }

    private fun initView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun setPlayers(players: List<Player>) {
        this.players = players
        val adapter = PlayersAdapter()
        adapter.setOnItemClickListener(this)
        adapter.setData(players)
        recycler_view.adapter = adapter
    }

    fun displayAddPlayerDialog(view: View) {

        if (players?.size!! >= 10)
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
                    presenter.addPlayer(Player(name))

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
            presenter.deletePlayer(Player(playerName))
            deletePlayerDialog!!.dialog.dismiss()
        }

        deletePlayerDialog?.cancelButton?.setOnClickListener {
            deletePlayerDialog!!.dialog.dismiss()
        }
    }

    fun exit(view: View) = finish()
}