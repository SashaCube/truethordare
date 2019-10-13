package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game

import androidx.lifecycle.ViewModel
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.ContentRepository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.PlayerRepository

class GameViewModel(playerRepository: PlayerRepository, contentRepository: ContentRepository) : ViewModel(),
    Game.Controller{

    val players = playerRepository.getPlayers()
    val repositoryState = contentRepository.getState()
    val questions = contentRepository.getQuestions()
    val actions = contentRepository.getActions()

    override fun onQuestionOrActionCancelClick(playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQuestionClick(playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActionClick(playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQuestionCancelClick(question: String, playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQuestionOkClick(question: String, playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActionCancelClick(action: String, playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActionOkClick(action: String, playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBottleClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBottleSpinAnimationEnd(playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBottleSpinAnimationStart(playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}