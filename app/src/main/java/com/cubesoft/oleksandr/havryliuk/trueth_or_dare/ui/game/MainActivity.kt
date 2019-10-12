package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.game

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.R
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.players.EditPlayersActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.GameView
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.game.PlayersManager
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.info.InfoActivity
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.ui.util.log
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.yesButton
import java.util.*


class MainActivity : Activity(), Animation.AnimationListener {

    private lateinit var mGameView: GameView
    private var actions: MutableList<String> = mutableListOf()
    private var questions: MutableList<String> = mutableListOf()

    private lateinit var mBottleImageView: ImageView
    private var lastAngle = -1
    private lateinit var nextPlayer: String
    private var nextIndex: Int = 0
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var mFirebaseFirestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PlayersManager.init(applicationContext)
        initFirebase()
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        initView()
        fetchDataFromDb()
    }

    private fun initFirebase() {
        initFirestore()
        initAnalytics()
    }

    private fun initAnalytics() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
    }

    private fun initFirestore() {
        mFirebaseFirestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        mFirebaseFirestore.firestoreSettings = settings
    }

    private fun initView() {
        mGameView = find(R.id.game_view)
        mBottleImageView = find(R.id.bottle_image_view)

        exit_button.setOnClickListener {
            mFirebaseAnalytics.log("OnExitClick")
            finish()
        }
        edit_player_button.setOnClickListener {
            startActivity(Intent(this, EditPlayersActivity::class.java))
            mFirebaseAnalytics.log("OnEditPlayerClick")
        }
        info_button.setOnClickListener {
            startActivity(Intent(this, InfoActivity::class.java))
            mFirebaseAnalytics.log("OnInfoClick")
        }
        bottle_image_view.setOnClickListener {
            if (PlayersManager.players().isEmpty()) {
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

    private fun fetchDataFromDb() {
        val docRef: DocumentReference = mFirebaseFirestore.collection("content")
            .document("77ILQNof8dn9m8Qh551P")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val questions = document.data?.get("questions") as MutableList<String>
                    val actions = document.data?.get("actions") as MutableList<String>
                    updateDate(actions = actions, questions = questions)

                    Log.d(TAG, "DocumentSnapshot data: $questions")
                    Log.d(TAG, "DocumentSnapshot data: $actions")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception as Throwable?)
                alert(
                    R.string.network_error_message,
                    R.string.network_error
                ) {
                    positiveButton("ОК") { finish() }
                    onCancelled { finish() }
                }.show()
            }

    }


    private fun bindPlayersWithUI() {
        Log.i("BindData_main", "bind players(${PlayersManager.size()}) to view")
        mGameView.setPlayers(PlayersManager.players())
    }

    private fun updateDate(
        actions: MutableList<String>? = null,
        questions: MutableList<String>? = null
    ) {
        Log.i("UpdateData_main", "update data")
        actions?.let { this.actions = actions }
        questions?.let { this.questions = questions }
    }

    private fun actionAlert(player: String) {
        actions.getRandom()?.let {
            alert(it, player) {
                positiveButton(R.string.done) { }
            }.show()
            mFirebaseAnalytics.log("ShowAction", "action: $it")
        }
    }

    private fun truthAlert(player: String) {
        questions.getRandom()?.let {
            alert(it, player) {
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
        val pivotX = mBottleImageView.width.div(2).toFloat()
        val pivotY = mBottleImageView.height.div(2).toFloat()

        nextPlayer = PlayersManager.getRandomPlayer()
        nextIndex = PlayersManager.players().indexOf(nextPlayer)

        val angle = Random().nextInt(7).times(360)
            .plus(nextIndex.times(360.div(PlayersManager.size())))
            .plus(Random().nextInt(360.div(PlayersManager.size()).div(2)))
            .plus(90)
            .plus(360)
        val animation =
            RotateAnimation(
                (if (lastAngle == -1) 0 else lastAngle).toFloat(),
                angle.toFloat(), pivotX, pivotY
            )

        lastAngle = angle//.rem(360)
        animation.duration = 2500
        animation.fillAfter = true

        animation.setAnimationListener(this)
        mBottleImageView.startAnimation(animation)
    }

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        val player = nextPlayer
        alert(
            getString(R.string.truth_or_dire), getString(
                R.string.player_choose, player)
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
        bindPlayersWithUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        PlayersManager.save()
    }

    private fun MutableList<String>.getRandom(): String? {
        if (size <= 1) {
            fetchDataFromDb()
        }
        val str = this[Random().nextInt(size)]
        this.remove(str)
        return str
    }

    companion object {
        const val TAG = "MainActivity"
    }
}