package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.presentation.game.state

import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.model.Player

class ActionState(
    override val player: Player,
    override val content: String,
    override val state: DialogShowingState
) : DialogState(player, content, state) {
    override fun getCompleteMessage() {}
}