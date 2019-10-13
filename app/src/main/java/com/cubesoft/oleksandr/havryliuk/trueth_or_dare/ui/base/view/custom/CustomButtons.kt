package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom

import android.widget.ImageView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Color
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Dimen
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.customView

inline fun _RelativeLayout.backButton(init: (ImageView).() -> Unit = {}) = Dimen.run {
    customView<ImageView> {
        init()
        setImageResource(R.drawable.ic_arrow_back)
    }.lparams(
        width = dip(ICON_SIZE),
        height = dip(ICON_SIZE)
    ) {
        margin = dip(SMALL)
    }
}

inline fun _RelativeLayout.addButton(init: (FloatingActionButton).() -> Unit = {}) = Dimen.run {
    customView<FloatingActionButton> {
        init()
        setImageResource(R.drawable.ic_add_white)
        rippleColor = Color.WHITE
    }.lparams {
        margin = dip(LARGE)
        alignParentBottom()
        alignParentEnd()
    }
}