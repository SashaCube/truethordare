package com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.storage.local.model.Player


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