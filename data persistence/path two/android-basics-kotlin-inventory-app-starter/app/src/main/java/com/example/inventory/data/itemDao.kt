package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    /* The database operations can take a long time to execute,
    so they should run on a separate thread. Make the function a suspend function,
    so that this function can be called from a coroutine.*/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Update
    suspend fun update(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * from item WHERE id = :id")
    fun getItem(id: Int): Flow<List<Item>>

    @Query("SELECT * from item ORDER BY name ASC")
    fun getItems(): Flow<List<Item>>

}