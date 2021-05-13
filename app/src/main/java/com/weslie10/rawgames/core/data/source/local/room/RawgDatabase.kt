package com.weslie10.rawgames.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.weslie10.rawgames.core.data.source.local.entity.GamesEntity

@Database(entities = [GamesEntity::class], version = 1, exportSchema = false)
abstract class RawgDatabase : RoomDatabase() {

    abstract fun rawgDao(): RawgDao

    companion object {
        @Volatile
        private var INSTANCE: RawgDatabase? = null

        fun getInstance(context: Context): RawgDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RawgDatabase::class.java,
                    "Tourism.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}