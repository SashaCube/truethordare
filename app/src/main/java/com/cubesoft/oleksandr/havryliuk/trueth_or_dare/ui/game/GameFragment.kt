package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom.*
import kotlinx.android.synthetic.main.game_fragment.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.verticalLayout
import javax.inject.Inject

class GameFragment @Inject constructor(): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(AnkoContext.create(ctx, this)) {
        relativeLayout {
            verticalLayout {
                relativeLayout {
                    titleTextView(R.string.app_name)
                }
                divider()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            findNavController().navigate(R.id.action_player_fragment)


//        game_info_btn_iv.setOnClickListener {
//            findNavController(this).navigate(R.id.action_info_fragment)
//        }
//
//        game_player_btn_iv.setOnClickListener {
//            findNavController(this).navigate(R.id.action_player_fragment)
//        }
//
//        game_exit_btn_iv.setOnClickListener {
//            activity?.finish()
//        }
    }
}