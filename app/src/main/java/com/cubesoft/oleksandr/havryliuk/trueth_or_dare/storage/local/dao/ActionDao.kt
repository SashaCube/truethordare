package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Action

@Dao
interface ActionDao {

    @get:Query("SELECT * FROM action_table")
    val all: List<Action>

    @Query("SELECT * FROM action_table WHERE id = :id")
    fun getById(id: Long): Action

    @Insert
    fun insert(action: Action)

    @Delete
    fun delete(action: Action)

    @Query("DELETE FROM action_table")
    fun deleteAll()
}