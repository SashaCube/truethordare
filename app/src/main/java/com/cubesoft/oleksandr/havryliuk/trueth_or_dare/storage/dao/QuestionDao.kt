package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Question

@Dao
interface QuestionDao {

    @get:Query("SELECT * FROM question_table")
    val all: List<Question>

    @Query("SELECT * FROM question_table WHERE id = :id")
    fun getById(id: Long): Question

    @Insert
    fun insert(question: Question)

    @Delete
    fun delete(question: Question)

}