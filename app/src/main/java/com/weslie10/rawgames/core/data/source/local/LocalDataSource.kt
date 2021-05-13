package com.weslie10.rawgames.core.data.source.local

import com.weslie10.rawgames.core.data.source.local.entity.GamesEntity
import com.weslie10.rawgames.core.data.source.local.room.RawgDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val rawgDao: RawgDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(rawgDao: RawgDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(rawgDao)
            }
    }

    fun getAllGames(): Flow<List<GamesEntity>> = rawgDao.getAllGames()

    fun getFavoriteGames(): Flow<List<GamesEntity>> = rawgDao.getFavoriteGames()

    suspend fun insertGames(rawgList: List<GamesEntity>) = rawgDao.insertGames(rawgList)

    fun setFavoriteGames(games: GamesEntity, newState: Boolean) {
        games.isFavorite = newState
        rawgDao.updateFavoriteGames(games)
    }
}