package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities= [Item::class], version=1, exportSchema = false)
// why abstract? because Room will create the full implementation for us
abstract class InventoryDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object{
        @Volatile //the value will never be cached, changes to it in one thread will be visible in all other threads
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .fallbackToDestructiveMigration() // defines the migration strategy for changing the schema of the database
                    .build().also {
                        Instance = it
                    }
            }
        }
    }
}