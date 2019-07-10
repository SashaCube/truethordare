package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Action
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Question
import java.util.*

fun List<Question>.getRandom(): Question? = if (isEmpty()) null else this[Random().nextInt(size)]

fun List<Action>.getRandom(): Action? = if (isEmpty()) null else this[Random().nextInt(size)]

fun List<Player>.getRandom(): Player? = if (isEmpty()) null else this[Random().nextInt(size)]

fun List<Player>.getAllNames(): List<String> {
    val list = mutableListOf<String>()
    for (p in this) list.add(p.name)
    return list
}
