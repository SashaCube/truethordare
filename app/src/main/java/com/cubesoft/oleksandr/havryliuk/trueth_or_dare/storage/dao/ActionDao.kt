package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Action

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