package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.info.InfoActivity
import kotlinx.android.synthetic.main.activity_main.*

class GameActivity : AppCompatActivity(), Game.View{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        keepScreenOn()
        initView()
    }

    private fun keepScreenOn() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    private fun initView() {
        game_view
        bottle_image_view

        exit_button.setOnClickListener {}
        edit_player_button.setOnClickListener {}
        info_button.setOnClickListener {}
        bottle_image_view.setOnClickListener {}
    }

    override fun showQuestionOrAction(playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showQuestion(playerName: String, question: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showAction(playerName: String, action: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun startBottleSpinAnimation(angle: Float, playerName: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun stopBottleSpinAnimation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addPlayersToBoard(player: List<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}