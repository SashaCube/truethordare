package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.managers

import java.util.*

class GameManager(
    var actions: MutableList<String> = mutableListOf("Встань з-за столу і голосно занявкали, як кіт. Або загавкали, як собака."),
    var questions: MutableList<String> = mutableListOf("Якби тобі запропонували потрапити на обкладинку будь-якого журналу, то яку б ти вибрав?")
) {
    companion object {
        private var instance: GameManager? = null
        fun instance() = instance!!
    }

    init {
        instance = this
    }

    var usedActions: MutableList<String> = mutableListOf()
    var usedQuestions: MutableList<String> = mutableListOf()

    fun getRandomQuestion(): String {
        if (questions.isEmpty()) {
            questions = usedQuestions
            usedQuestions = mutableListOf()
        }

        val question = questions[Random().nextInt(questions.size)]
        questions.remove(question)
        usedQuestions.add(question)
        return question
    }

    fun getRandomAction(): String {
        if (actions.isEmpty()) {
            actions = usedActions
            usedActions = mutableListOf()
        }

        val action = actions[Random().nextInt(actions.size)]
        actions.remove(action)
        usedActions.add(action)
        return action
    }
}