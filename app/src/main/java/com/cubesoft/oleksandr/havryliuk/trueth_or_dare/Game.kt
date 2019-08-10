package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

interface Game {

    interface View {

        fun addPlayersToBoard(player: List<String>)

        fun showQuestionOrAction(playerName: String)

        fun showQuestion(playerName: String, question: String)

        fun showAction(playerName: String, action: String)

        fun startBottleSpinAnimation(angle: Float, playerName: String)

        fun stopBottleSpinAnimation()
    }

    interface Controller{

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
}