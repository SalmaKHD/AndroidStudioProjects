package com.example.inventory.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao // provides an abstraction layer for accessing and modifying the database from the rest of the app
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE) // what happens when we try to insert items from within two places at the same time? won't happen.
    suspend fun insert(item: Item)
    @Update
    suspend fun update(item: Item)
    @Delete
    suspend fun delete(item: Item)
    @Query("SELECT * FROM item WHERE ID= :id")
    fun getItem(id: Int): Flow<Item>

    @Query("SELECT * FROM item ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}