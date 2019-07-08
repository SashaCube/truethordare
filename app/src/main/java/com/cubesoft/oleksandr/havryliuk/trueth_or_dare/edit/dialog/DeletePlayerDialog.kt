package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.dialog

import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import org.jetbrains.anko.*

private const val ACTIVITY_PADDING = 16

class DeletePlayerDialog(ui: AnkoContext<View>, playerName: String) {

    lateinit var dialog: DialogInterface
    lateinit var cancelButton: TextView
    lateinit var okButton: TextView

    init {
        with(ui) {
            dialog = alert {

                customView {
                    verticalLayout {
                        padding = dip(ACTIVITY_PADDING)


                        textView(context.resources.getString(R.string.delete_player_message, playerName)).lparams {
                            bottomMargin = dip(ACTIVITY_PADDING)
                        }

                        linearLayout {
                            topPadding = dip(24)
                            orientation = LinearLayout.HORIZONTAL
                            horizontalGravity = Gravity.END

                            cancelButton = textView(R.string.delete_player_cancel_label) {
                                textSize = 14f
                                textColor = context.resources.getColor(R.color.colorAccent)
                            }.lparams {
                                marginEnd = dip(ACTIVITY_PADDING)
                            }

                            okButton = textView(R.string.delete_player_submit_label) {
                                textSize = 14f
                                textColor = context.resources.getColor(R.color.colorAccent)
                            }
                        }
                    }
                }
            }.show()
        }
    }
}