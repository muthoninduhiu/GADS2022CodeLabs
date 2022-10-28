package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities =[Item::class], version = 1)
abstract class ItemRoomDatabase:RoomDatabase() {
    /*Define an abstract method or property that returns an ItemDao Instance and the
    Room will generate the implementation for you.*/
    abstract fun itemDao(): ItemDao
    /*You only need one instance of the RoomDatabase for the whole app,
     so make the RoomDatabase a singleton.*/
    companion object{
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null
        fun getDatabase(context: Context): ItemRoomDatabase {

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemRoomDatabase::class.java,
                    "item_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}