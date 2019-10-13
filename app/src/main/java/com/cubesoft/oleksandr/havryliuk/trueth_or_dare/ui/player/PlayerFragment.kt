package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom.*
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player.adapter.PlayersAdapter
import kotlinx.android.synthetic.main.player_fragment.*
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.ctx

class PlayerFragment : Fragment() {

    private val playerAdapter by lazy { PlayersAdapter() }

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
                    adapter = playerAdapter
                }.lparams(width = matchParent)
            }
            addButton {
                setOnClickListener {
                    toast("add player")
                }
            }
        }
    }
}