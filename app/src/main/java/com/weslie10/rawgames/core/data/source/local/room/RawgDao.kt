package com.weslie10.rawgames.core.data.source.local.room

import androidx.room.*
import com.weslie10.rawgames.core.data.source.local.entity.GamesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RawgDao {

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GamesEntity>>

    @Query("SELECT * FROM games where favorite = 1")
    fun getFavoriteGames(): Flow<List<GamesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGames(games: List<GamesEntity>)

    @Update
    fun updateFavoriteGames(games: GamesEntity)
}
