package com.example.projetograo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Grao::class], version = 1)
abstract class GraoDatabase : RoomDatabase() {
    abstract fun graoDao(): GraoDao

    companion object {
        @Volatile private var instance: GraoDatabase? = null

        fun getDatabase(context: Context): GraoDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    GraoDatabase::class.java,
                    "grain_database"
                ).build().also { instance = it }
            }
        }
    }
}
