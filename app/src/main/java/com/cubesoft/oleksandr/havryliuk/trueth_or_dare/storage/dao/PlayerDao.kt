package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.model.Player


@Dao
interface PlayerDao {

    @get:Query("SELECT * FROM player_table")
    val all: List<Player>

    @Query("SELECT * FROM player_table WHERE id = :id")
    fun getById(id: Long): Player

    @Insert
    fun insert(player: Player)

    @Delete
    fun delete(player: Player)

    @Query("DELETE FROM player_table WHERE name = :name")
    fun deleteByName(name: String)
}