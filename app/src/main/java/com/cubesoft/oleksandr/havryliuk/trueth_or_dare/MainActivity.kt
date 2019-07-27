package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.edit.EditPlayersActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.GameView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.info.InfoActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.DbWorkerThread
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.GameDatabase
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Action
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Question
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.populateActions
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.populateQuestions
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.remote.RemoteDatabase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.yesButton
import java.util.*


class MainActivity : Activity(), Animation.AnimationListener {

    private var mDb: GameDatabase? = null

    private lateinit var mGameView: GameView
    private lateinit var mDbWorkerThread: DbWorkerThread

    private lateinit var mUiHandler: Handler

    private var actions: List<Action> = listOf()
    private var questions: List<Question> = listOf()
    private var players: List<Player> = listOf()

    private lateinit var mBottleImageView: ImageView
    private var lastAngle = -1
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // firebase firestore test
        val remoteDatabase = RemoteDatabase(FirebaseFirestore.getInstance())
        val actions = resources.getStringArray(R.array.default_actions).asList()
        val questions = resources.getStringArray(R.array.default_questions).asList()
        remoteDatabase.uploadContent(actions = actions, questions = questions)

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        mUiHandler = Handler()
        mDbWorkerThread = DbWorkerThread("dbWorkerThread_main")
        mDbWorkerThread.start()

        mDb = GameDatabase.getInstance(this)

        initView()

        fetchDataFromDb()
    }

    private fun initView() {
        mGameView = find(R.id.game_view)
        mBottleImageView = find(R.id.bottle_image_view)

        find<ImageView>(R.id.exit_button).setOnClickListener {
            mFirebaseAnalytics.log("OnExitClick")
            finish()
        }
        find<ImageView>(R.id.edit_player_button).setOnClickListener {
            startActivity(Intent(this, EditPlayersActivity::class.java))
            mFirebaseAnalytics.log("OnEditPlayerClick")
        }
        find<ImageView>(R.id.info_button).setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
            mFirebaseAnalytics.log("OnInfoClick")
        }
        find<ImageView>(R.id.bottle_image_view).setOnClickListener {
            if (players.isEmpty()) {
                alert(R.string.add_one_player_to_start) {
                    yesButton { }
                }.show()
                mFirebaseAnalytics.log("OnBottleClick", "No users")
            } else {
                spinBottle()
                mFirebaseAnalytics.log("OnBottleClick", "Spin bottle")
            }
        }
    }

    private fun fetchPlayersFromDb() {
        val task = Runnable {
            val players = mDb?.playerDao()?.all
            mUiHandler.post {
                if (players == null || players.isEmpty()) {
                    Log.i("FetchPlayersFromDb_main", "not players")
                } else {
                    Log.i("FetchPlayersFromDb_main", "fetch and update ${players.size} players")
                    players.let { this.players = players }
                    bindDataWithUI()
                }
            }
        }
        mDbWorkerThread.postTask(task)
    }

    private fun fetchDataFromDb() {
        val task = Runnable {
            var actions = mDb?.actionDao()?.all
            var questions = mDb?.questionDao()?.all

            if (actions == null || actions.isEmpty()) {
                Log.i("FetchDataFromDb_main", "no actions -> populate database")
                mDb?.actionDao()?.let {
                    populateActions(
                        it,
                        resources.getStringArray(R.array.default_actions).asList()
                    )
                }
                actions = mDb?.actionDao()?.all
            }

            if (questions == null || questions.isEmpty()) {
                Log.i("FetchDataFromDb_main", "no questions -> populate database")
                mDb?.questionDao()?.let {
                    populateQuestions(
                        it,
                        resources.getStringArray(R.array.default_questions).asList()
                    )
                }
                questions = mDb?.questionDao()?.all
            }

            Log.i("FetchDataFromDb_main", "update: ${actions?.size} -> actions; ${questions?.size} -> questions")
            mUiHandler.post {
                updateDate(actions = actions, questions = questions)
            }
        }
        mDbWorkerThread.postTask(task)
    }


    private fun bindDataWithUI() {
        Log.i("BindData_main", "bind players(${players.size}) to view")
        mGameView.setPlayers(players.getAllNames())
    }

    private fun updateDate(
        actions: List<Action>? = null,
        questions: List<Question>? = null
    ) {
        Log.i("UpdateData_main", "update data")
        actions?.let { this.actions = actions }
        questions?.let { this.questions = questions }
    }

    private fun actionAlert(player: Player) {
        actions.getRandom()?.action?.let {
            alert(it, player.name) {
                positiveButton(R.string.done) { }
            }.show()
            mFirebaseAnalytics.log("ShowAction", "action: $it")
        }
    }

    private fun truthAlert(player: Player) {
        questions.getRandom()?.question?.let {
            alert(it, player.name) {
                positiveButton(R.string.done) { }
            }.show()
            mFirebaseAnalytics.log("ShowQuestion", "question: $it")
        }
    }

    private fun appearAnimation() {
        val fadeIn = AlphaAnimation(0f, 1f)
        fadeIn.interpolator = DecelerateInterpolator() //add this
        fadeIn.duration = 1000

        (find(R.id.main_layout) as View).animation = fadeIn
    }

    private fun spinBottle() {
        val angle = Random().nextInt(3600 - 360) + 90 + (360 - lastAngle.plus(90).rem(360))

        val pivotX = mBottleImageView.width.div(2).toFloat()
        val pivotY = mBottleImageView.height.div(2).toFloat()

        val animation =
            RotateAnimation(
                (if (lastAngle == -1) 0 else lastAngle.plus(90).rem(360)).toFloat(),
                angle.toFloat(), pivotX, pivotY
            )
        lastAngle = angle.minus(90).rem(360)
        animation.duration = 2500
        animation.fillAfter = true

        animation.setAnimationListener(this)
        mBottleImageView.startAnimation(animation)
    }

    private fun resetBottle() {
        val pivotX = mBottleImageView.width.div(2).toFloat()
        val pivotY = mBottleImageView.height.div(2).toFloat()

        val animation = RotateAnimation((if (lastAngle == -1) 0 else lastAngle).toFloat(), 0f, pivotX, pivotY)
        lastAngle = -1
        animation.duration = 2000
        animation.fillAfter = true

        mBottleImageView.startAnimation(animation)
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        val index = lastAngle.div(360.div(players.size))
        val player = players[index]

        alert(
            getString(R.string.truth_or_dire), getString(R.string.player_choose, player.name)
        ) {
            positiveButton(R.string.action) {
                actionAlert(player)
                mFirebaseAnalytics.log("AfterSpinChoose", "Action")
            }
            negativeButton(R.string.truth) {
                truthAlert(player)
                mFirebaseAnalytics.log("AfterSpinChoose", "Question")
            }
        }.show()

    }

    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onStart() {
        super.onStart()
        appearAnimation()
        fetchPlayersFromDb()
    }

    override fun onDestroy() {
        super.onDestroy()
        mDbWorkerThread.quit()
        GameDatabase.destroyInstance()
    }
}
