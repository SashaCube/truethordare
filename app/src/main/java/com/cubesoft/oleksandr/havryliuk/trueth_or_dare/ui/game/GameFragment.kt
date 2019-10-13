package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import kotlinx.android.synthetic.main.game_fragment.*

class GameFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.game_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        game_info_btn_iv.setOnClickListener {
            findNavController(this).navigate(R.id.action_info_fragment)
        }

        game_player_btn_iv.setOnClickListener {
            findNavController(this).navigate(R.id.action_player_fragment)
        }

        game_exit_btn_iv.setOnClickListener {
            activity?.finish()
        }
    }
}