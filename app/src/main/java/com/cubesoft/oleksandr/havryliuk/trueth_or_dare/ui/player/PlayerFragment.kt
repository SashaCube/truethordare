package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import kotlinx.android.synthetic.main.player_fragment.*

class PlayerFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.player_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player_back_btn_iv.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}