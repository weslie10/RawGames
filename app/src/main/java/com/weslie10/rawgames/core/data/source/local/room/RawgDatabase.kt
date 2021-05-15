package com.weslie10.rawgames.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.weslie10.rawgames.core.data.source.local.entity.GamesEntity

@Database(entities = [GamesEntity::class], version = 1, exportSchema = false)
abstract class RawgDatabase : RoomDatabase() {
    abstract fun rawgDao(): RawgDao
}