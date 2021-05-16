package com.weslie10.rawgames.core.data.source.local

import android.util.Log
import com.weslie10.rawgames.core.data.source.local.entity.GamesEntity
import com.weslie10.rawgames.core.data.source.local.room.RawgDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val rawgDao: RawgDao) {

    fun getDetailGames(id: Int): Flow<GamesEntity> = rawgDao.getDetailGames(id)

    fun getFavoriteGames(): Flow<List<GamesEntity>> = rawgDao.getFavoriteGames()

    suspend fun insertGames(games: GamesEntity) = rawgDao.insertGames(games)

    fun setFavoriteGames(games: GamesEntity, state: Boolean) {
        games.isFavorite = state
        Log.d("state", games.isFavorite.toString())
        rawgDao.updateFavoriteGames(games)
    }
}