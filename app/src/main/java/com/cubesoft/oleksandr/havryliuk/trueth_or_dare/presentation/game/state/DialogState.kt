package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.game.state

import android.content.Context
import android.content.DialogInterface
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player
import org.jetbrains.anko.alert

abstract class DialogState(
    open val player: Player,
    open val content: String,
    open val state: DialogShowingState
) {
    abstract fun getCompleteMessage()

    fun doAction(
        context: Context,
        dialog: DialogInterface,
        onCancel: () -> Unit,
        onShow: () -> Unit
    ) = when (state) {
        DialogShowingState.SHOW -> {
            context.alert(content, player.name) {
                positiveButton(R.string.done) { }
            }.build().show()
            onShow()
        }
        DialogShowingState.CANCEL -> {
            dialog.dismiss()
            onCancel()
        }
    }
}