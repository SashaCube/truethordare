package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Color
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Dimen
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Style
import org.jetbrains.anko.*

class PlayersAdapter
    (
    private var onDeleteClick: (player: Player) -> Unit
) :
    RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    private var players: List<Player> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = with(AnkoContext.create(parent.context, parent)) {
            relativeLayout {
                setBackgroundColor(Color.WHITE)
                padding = Dimen.DEFAULT
                textView {
                    id = R.id.player_item_player_name
                    textSize = Dimen.LARGE_TEXT_SIZE
                    typeface = Style.BOLD
                    padding = Dimen.DEFAULT
                    maxLines = 1
                    gravity = Gravity.CENTER
                    setBackgroundColor(Color.WHITE)
                }.lparams(width = matchParent)
                imageView(R.drawable.ic_x_button) {
                    id = R.id.player_item_delete_button
                    padding = Dimen.DEFAULT
                }.lparams {
                    alignParentEnd()
                }
            }
        }
        return ViewHolder(view)
    }

    override fun getItemCount() = players.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPlayer = players[position]

        holder.playerName.text = currentPlayer.name

        holder.deleteButton.setOnClickListener {
            onDeleteClick(currentPlayer)
        }
    }

    fun setData(players: List<Player>) {
        this.players = players
        notifyDataSetChanged()
    }

    class ViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val playerName: TextView = view.find(R.id.player_item_player_name)
        val deleteButton: ImageView = view.find(R.id.player_item_delete_button)
    }
}