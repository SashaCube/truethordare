package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom

import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Color
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Dimen
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Style
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util.getHtmlText
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.customView

inline fun _RelativeLayout.titleTextView(id: Int, init: (TextView).() -> Unit = {}) =
    customView<TextView> {
        init()

        textResource = id

        textSize = Dimen.LARGE_TEXT_SIZE
        padding = dip(Dimen.DEFAULT)
        textColor = Color.PRIMARY
        gravity = Gravity.CENTER
        typeface = Style.BOLD

    }.lparams(width = matchParent)

inline fun _LinearLayout.simpleTextView(id: Int, init: (TextView).() -> Unit = {}) =
    customView<TextView> {
        init()

        textResource = id

        padding = dip(Dimen.DEFAULT)
        textColor = Color.BLACK
        gravity = Gravity.CENTER
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        setLineSpacing(1f, 2f)
        typeface = Style.ITALIC
    }.lparams(width = matchParent) {
        topMargin = dip(Dimen.DEFAULT)
    }

inline fun _RelativeLayout.linkTextView(id: Int, init: (TextView).() -> Unit = {}) =
    customView<TextView> {
        init()

        text = getHtmlText(id)
        gravity = Gravity.CENTER
        textColor = Color.BLACK
        isClickable = true
        setLinkTextColor(Color.PRIMARY)
        linksClickable = true
        typeface = Style.ITALIC
    }.lparams(width = matchParent) {
        bottomMargin = dip(Dimen.LARGE)
        alignParentBottom()
    }