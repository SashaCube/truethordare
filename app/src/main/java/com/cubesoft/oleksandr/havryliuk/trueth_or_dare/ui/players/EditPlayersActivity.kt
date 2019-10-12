package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.players

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.PlayersManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.PlayersManager.Companion.MAX_PLAYERS
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.players.adapter.PlayersAdapter
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.players.dialog.AddPlayerDialog
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.players.dialog.DeletePlayerDialog
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util.log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.analytics.FirebaseAnalytics
import org.jetbrains.anko.*

class EditPlayersActivity : Activity(),
    EditPlayersContract.IEditPlayersView,
    PlayersAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlayersAdapter
    private lateinit var presenter: EditPlayersContract.IEditPlayersPresenter
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics


    override fun onItemClickListener(v: View, pos: Int) {
        displayDeletePlayerDialog(v, PlayersManager.players()[pos])
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_players)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        initView()
        presenter =
            EditPlayersPresenter(this)
    }

    private fun initView() {
        recyclerView = find(R.id.recycler_view)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        adapter =
            PlayersAdapter()
        adapter.setOnItemClickListener(this)

        recyclerView.adapter = adapter

        find<ImageView>(R.id.back_button_edit).setOnClickListener {
            mFirebaseAnalytics.log("OnBackClick", "From EditPlayerActivity to MainActivity")
            finish()
        }
        find<FloatingActionButton>(R.id.add_fab).setOnClickListener { displayAddPlayerDialog() }
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun setPlayers(players: List<String>) {
        adapter.setData(players)
    }

    private fun displayAddPlayerDialog() {

        if (PlayersManager.size() >= MAX_PLAYERS) {
            alert(R.string.max_amount_of_player) {
                yesButton { }
            }.show()
            mFirebaseAnalytics.log("OnAddPlayerClick", "don't added player because max count is 10")
        } else {

            val addPlayerDialog by lazy {
                contentView?.let {
                    AddPlayerDialog(
                        AnkoContext.create(this, it)
                    )
                }
            }

            addPlayerDialog?.okButton?.setOnClickListener {
                val name = addPlayerDialog!!.newPlayerName.text.toString()

                if (name != "")
                    presenter.addPlayer(name)

                addPlayerDialog!!.dialog.dismiss()
                mFirebaseAnalytics.log("OnAddPlayerClick", "add player")
            }

            addPlayerDialog?.cancelButton?.setOnClickListener {
                addPlayerDialog!!.dialog.dismiss()
                mFirebaseAnalytics.log("OnAddPlayerClick", "dismiss")
            }
        }
    }

    private fun displayDeletePlayerDialog(view: View, playerName: String) {
        val deletePlayerDialog by lazy {
            contentView?.let {
                DeletePlayerDialog(
                    AnkoContext.create(
                        this,
                        it
                    ), playerName
                )
            }
        }

        deletePlayerDialog?.okButton?.setOnClickListener {
            presenter.deletePlayer(playerName)
            deletePlayerDialog!!.dialog.dismiss()
            mFirebaseAnalytics.log("OnDeletePlayerClick", "delete player")
        }

        deletePlayerDialog?.cancelButton?.setOnClickListener {
            deletePlayerDialog!!.dialog.dismiss()
            mFirebaseAnalytics.log("OnDeletePlayerClick", "dismiss")
        }
    }
}