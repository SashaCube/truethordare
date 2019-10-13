package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player.dialog


import android.content.DialogInterface
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.LinearLayout
import android.widget.TextView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

private const val ACTIVITY_PADDING = 16

class AddPlayerDialog(ui: AnkoContext<View>) {

    lateinit var dialog: DialogInterface
    lateinit var newPlayerName: TextInputEditText
    lateinit var cancelButton: TextView
    lateinit var okButton: TextView

    init {
        with(ui) {
            dialog = alert {

                customView {
                    verticalLayout {
                        padding = dip(ACTIVITY_PADDING)

                        textView(R.string.add_player_title) {
                            textSize = 24f
                            textColor = context.resources.getColor(R.color.black)
                        }.lparams {
                            bottomMargin = dip(ACTIVITY_PADDING)
                        }

                        textView(R.string.add_player_message).lparams {
                            bottomMargin = dip(ACTIVITY_PADDING)
                        }

                        textInputLayout {
                            newPlayerName = textInputEditText {
                                textSize = 16f
                            }
                        }

                        linearLayout {
                            topPadding = dip(24)
                            orientation = LinearLayout.HORIZONTAL
                            horizontalGravity = Gravity.END

                            cancelButton = textView(R.string.add_player_cancel_label) {
                                textSize = 14f
                                textColor = context.resources.getColor(R.color.colorAccent)
                            }.lparams {
                                marginEnd = dip(ACTIVITY_PADDING)
                            }

                            okButton = textView(R.string.add_player_submit_label) {
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

inline fun ViewManager.textInputEditText(theme: Int = 0, init: TextInputEditText.() -> Unit) =
    ankoView({ TextInputEditText(it) }, theme, init)

inline fun ViewManager.textInputLayout(theme: Int = 0, init: TextInputLayout.() -> Unit) =
    ankoView({ TextInputLayout(it) }, theme, init)