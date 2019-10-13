package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.player

import android.content.Context
import android.view.Gravity
import android.view.ViewManager
import android.widget.EditText
import android.widget.LinearLayout
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Color
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.Dimen
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.base.view.custom.dialogButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

fun Context.addPlayerAlertBuilder(
    onSubmit: (name: String) -> Unit,
    onCancel: () -> Unit
) = alert {
    customView {
        verticalLayout {
            padding = dip(Dimen.LARGE)

            lateinit var newPlayerName: EditText

            textView(R.string.add_player_title) {
                textSize = Dimen.LARGE_TEXT_SIZE
                textColor = Color.BLACK
            }.lparams {
                bottomMargin = dip(Dimen.LARGE)
            }

            textView(R.string.add_player_message).lparams {
                bottomMargin = dip(Dimen.LARGE)
            }

            textInputLayout {
                newPlayerName = textInputEditText()
            }

            linearLayout {
                topPadding = dip(Dimen.LARGE)
                orientation = LinearLayout.HORIZONTAL
                horizontalGravity = Gravity.END

                dialogButton(R.string.add_player_cancel_label) { onCancel() }

                dialogButton(R.string.add_player_submit_label) {
                    newPlayerName.text.toString().let { name ->
                        if (name.isNotEmpty()) {
                            onSubmit(name)
                        }
                    }
                }
            }
        }
    }
}

fun Context.deletePlayerAlertBuilder(
    playerName: String,
    onSubmit: () -> Unit,
    onCancel: () -> Unit
) = alert {
    customView {
        verticalLayout {
            padding = dip(Dimen.LARGE)
            textView(
                context.resources.getString(R.string.delete_player_message, playerName)
            ).lparams { bottomMargin = dip(Dimen.LARGE) }
            linearLayout {
                topPadding = dip(Dimen.LARGE)
                horizontalGravity = Gravity.END

                dialogButton(R.string.delete_player_cancel_label) { onCancel() }

                dialogButton(R.string.delete_player_submit_label) { onSubmit() }
            }
        }
    }
}

inline fun ViewManager.textInputEditText(theme: Int = 0, init: TextInputEditText.() -> Unit = {}) =
    ankoView({ TextInputEditText(it) }, 0, init)

inline fun ViewManager.textInputLayout(theme: Int = 0, init: TextInputLayout.() -> Unit = {}) =
    ankoView({ TextInputLayout(it) }, 0, init)