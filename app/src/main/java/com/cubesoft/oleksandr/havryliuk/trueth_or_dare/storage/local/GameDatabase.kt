package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import android.util.Log
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.dao.ActionDao
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.dao.PlayerDao
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.dao.QuestionDao
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Action
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Player
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Question


@Database(entities = [Player::class, Action::class, Question::class], version = 1)
abstract class GameDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun actionDao(): ActionDao
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getInstance(context: Context): GameDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "Game.db"
                ).build()

                INSTANCE = instance
                return instance
            }
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}

fun populateActions(dao: ActionDao, actions: List<String>) {
    Log.i("PopulateActions", "populate ${actions.size} action")
    for (str in actions) dao.insert(Action(null, str))
}

fun populateQuestions(dao: QuestionDao, questions: List<String>) {
    Log.i("PopulateQuestions", "populate ${questions.size} question")
    for (str in questions) dao.insert(Question(null, str))
}