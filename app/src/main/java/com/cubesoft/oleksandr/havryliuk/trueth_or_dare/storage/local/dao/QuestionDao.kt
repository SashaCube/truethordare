package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Question

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

    @Query("DELETE FROM question_table")
    fun deleteAll()
}