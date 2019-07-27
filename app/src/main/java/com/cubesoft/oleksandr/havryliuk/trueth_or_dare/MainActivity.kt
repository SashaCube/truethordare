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
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Player
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import org.jetbrains.anko.alert
import org.jetbrains.anko.find
import org.jetbrains.anko.yesButton
import java.util.*


class MainActivity : Activity(), Animation.AnimationListener {

    private var mDb: GameDatabase? = null

    private lateinit var mGameView: GameView
    private lateinit var mDbWorkerThread: DbWorkerThread

    private lateinit var mUiHandler: Handler

    private var actions: List<String> = listOf()
    private var questions: List<String> = listOf()
    private var players: List<Player> = listOf()

    private lateinit var mBottleImageView: ImageView
    private var lastAngle = -1
    private lateinit var mFirebaseAnalytics: FirebaseAnalytics
    private lateinit var mFirebaseFirestore: FirebaseFirestore


    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // firebase firestore test
        mFirebaseFirestore = FirebaseFirestore.getInstance()

        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        mFirebaseFirestore.firestoreSettings = settings

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
        val docRef: DocumentReference = mFirebaseFirestore.collection("content")
            .document("77ILQNof8dn9m8Qh551P")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val questions: List<String> = document.data?.get("questions") as List<String>
                    val actions = document.data?.get("actions") as List<String>
                    updateDate(actions = actions, questions = questions)

                    Log.d(TAG, "DocumentSnapshot data: $questions")
                    Log.d(TAG, "DocumentSnapshot data: $actions")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception as Throwable?)
                alert(R.string.network_error_message, R.string.network_error) {
                    positiveButton("ОК") { finish() }
                }.show()
            }

    }


    private fun bindDataWithUI() {
        Log.i("BindData_main", "bind players(${players.size}) to view")
        mGameView.setPlayers(players.getAllNames())
    }

    private fun updateDate(
        actions: List<String>? = null,
        questions: List<String>? = null
    ) {
        Log.i("UpdateData_main", "update data")
        actions?.let { this.actions = actions }
        questions?.let { this.questions = questions }
    }

    private fun actionAlert(player: Player) {
        actions.getRandom()?.let {
            alert(it, player.name) {
                positiveButton(R.string.done) { }
            }.show()
            mFirebaseAnalytics.log("ShowAction", "action: $it")
        }
    }

    private fun truthAlert(player: Player) {
        questions.getRandom()?.let {
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
