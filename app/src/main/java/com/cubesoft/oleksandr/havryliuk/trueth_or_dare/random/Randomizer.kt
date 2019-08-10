package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.random

abstract class Randomizer {

    var basis = 1
        set(value) {
            field = if (value < 1) {
                1
            } else {
                value
            }
            restart()
        }

    abstract fun restart()

    abstract fun getNext(): Int
}