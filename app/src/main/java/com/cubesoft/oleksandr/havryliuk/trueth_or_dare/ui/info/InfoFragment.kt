package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom.*
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.verticalLayout

class InfoFragment : Fragment() {

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
                    titleTextView(R.string.about_game_label)
                }
                divider()
                simpleTextView(R.string.game_rules)
            }
            linkTextView(R.string.privacy_policy)
        }
    }
}