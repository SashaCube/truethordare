package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.random

import kotlin.random.Random

class RhythmicalRandom : Randomizer() {

    private var values = mutableListOf<Int>()

    override fun restart() {
        values = (1..basis).toMutableList()
    }

    override fun getNext(): Int {
        if(values.isEmpty()){
            restart()
        }

        val index = Random.nextInt(values.size)
        val result = values[index]
        values.removeAt(index)

        return result
    }
}