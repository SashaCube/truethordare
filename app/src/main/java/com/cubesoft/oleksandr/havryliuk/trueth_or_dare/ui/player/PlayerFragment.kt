package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.PlayerViewModel
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.PlayerViewModel.Companion.DISMISS
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.PlayerViewModel.Companion.SHOW
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Color
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

class PlayerFragment @Inject constructor(
    private val playerViewModel: PlayerViewModel
) : Fragment(), IPlayerView {

    private val playerAdapter by lazy {
        PlayersAdapter(onDeleteClick = { player ->
            playerViewModel.onDeleteClick(player)
        })
    }

    private var addPlayerDialog: DialogInterface? = null
    private var deletePlayerDialog: DialogInterface? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(AnkoContext.create(ctx, this)) {
        relativeLayout {
            verticalLayout {
                relativeLayout {
                    backButton {
                        setOnClickListener {
                            findNavController().popBackStack()
                        }
                    }
                    titleTextView(R.string.edit_players_label)
                }
                divider()
                recycler {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = playerAdapter
                }.lparams(width = matchParent, height = matchParent)
            }.lparams(height = matchParent)
            addButton {
                setOnClickListener {
                    playerViewModel.onAddClick()
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val owner = this
        playerViewModel.run {
            players.observe(owner, Observer { players ->
                playerAdapter.setData(players)
            })
            addAlarmDialogState.observe(owner, Observer { state ->
                when (state) {
                    SHOW -> showDialogAddPlayer()
                    DISMISS -> dismissDialogAddPlayer()
                }
            })
            deleteAlarmDialogState.observe(owner, Observer { pair ->
                when (pair.first) {
                    SHOW -> showDialogDeletePlayer(pair.second)
                    DISMISS -> dismissDialogAddPlayer()
                }
            })
            maxUsersAlarmDialogState.observe(owner, Observer { state ->
                when (state) {
                    SHOW -> showDialogMaxPlayersCount()
                }
            })
        }
    }

    override fun showDialogAddPlayer() {
        addPlayerDialog = context?.addPlayerAlertBuilder(
            onCancel = { playerViewModel.onDialogAddPlayerCancel() },
            onSubmit = { name -> playerViewModel.onDialogAddPlayerName(name) }
        )?.show()
    }

    override fun showDialogMaxPlayersCount() {
        alert(R.string.max_amount_of_player).show()
    }

    override fun showDialogDeletePlayer(player: Player) {
        addPlayerDialog = context?.deletePlayerAlertBuilder(
            player.name,
            onCancel = { playerViewModel.onDialogDeleteCancel(player) },
            onSubmit = { playerViewModel.onDialogDelete(player) }
        )?.show()
    }

    override fun dismissDialogAddPlayer() {
        addPlayerDialog?.dismiss()
    }

    override fun dismissDialogDeletePlayer() {
        deletePlayerDialog?.dismiss()
    }
}