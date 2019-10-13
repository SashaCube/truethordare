package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Color
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Dimen
import org.jetbrains.anko._LinearLayout
import org.jetbrains.anko.custom.customView
import org.jetbrains.anko.dip
import org.jetbrains.anko.matchParent

inline fun _LinearLayout.divider(init: (View).() -> Unit = {}) =
    customView<View> {
        init()

        setBackgroundColor(Color.PRIMARY)
    }.lparams(
        width = matchParent,
        height = dip(1)
    ) {
        marginEnd = dip(Dimen.DEFAULT)
        marginStart = dip(Dimen.DEFAULT)
    }

inline fun _LinearLayout.recycler(init: (RecyclerView).() -> Unit = {}) =
    customView<RecyclerView> {
        init()

        setBackgroundColor(Color.PRIMARY)
    }.lparams(
        width = matchParent
    ) {
        marginEnd = dip(Dimen.DEFAULT)
        marginStart = dip(Dimen.DEFAULT)
    }