package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.game

import androidx.lifecycle.LiveData
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.game.state.DialogState

interface IPlayerViewModel {

    val bottleSpinState: LiveData<Boolean>

    val afterBottleAlertDialogState: LiveData<DialogState>

    val questionAlertDialogState: LiveData<DialogState>

    val actionAlertDialogState: LiveData<DialogState>

    fun onStart()

    fun onQuestionOrActionCancelClick(playerName: String)

    fun onQuestionClick(playerName: String)

    fun onActionClick(playerName: String)

    fun onQuestionCancelClick(question: String, playerName: String)

    fun onQuestionOkClick(question: String, playerName: String)

    fun onActionCancelClick(action: String, playerName: String)

    fun onActionOkClick(action: String, playerName: String)

    fun onBottleClick()

    fun onBottleSpinAnimationEnd(playerName: String)

    fun onBottleSpinAnimationStart(playerName: String)
}