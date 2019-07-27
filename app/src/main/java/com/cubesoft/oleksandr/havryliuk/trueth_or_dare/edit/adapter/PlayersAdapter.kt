package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import org.jetbrains.anko.find

class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null
    private var players: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = players?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        players?.get(position)?.let { player ->
            holder.playerName.text = "${player}"
        }

        holder.deleteButton.setOnClickListener { v ->
            listener?.onItemClickListener(v, holder.layoutPosition)
        }
    }

    fun setData(players: List<String>) {
        this.players = players
        notifyDataSetChanged()
    }

    class ViewHolder(
        val view: View,
        val playerName: TextView = view.find(R.id.player_name_text),
        val deleteButton: ImageView = view.find(R.id.delete_button)
    ) : RecyclerView.ViewHolder(view)

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    interface OnItemClickListener {
        fun onItemClickListener(v: View, pos: Int)
    }
}