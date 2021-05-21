package com.weslie10.rawgames.core.data

import com.weslie10.rawgames.core.data.source.local.LocalDataSource
import com.weslie10.rawgames.core.data.source.remote.RemoteDataSource
import com.weslie10.rawgames.core.data.source.remote.network.ApiResponse
import com.weslie10.rawgames.core.data.source.remote.response.GamesResponse
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.domain.repository.IRawgRepository
import com.weslie10.rawgames.core.utils.AppExecutors
import com.weslie10.rawgames.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RawgRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IRawgRepository {

    override fun getAllGames(): Flow<List<Games>> {
        val data = remoteDataSource.getAllGames()
        return data.map { game ->
            DataMapper.mapListResponsesToListDomain(game)
        }
    }

    override fun getSearchGames(search: String): Flow<List<Games>> {
        val data = remoteDataSource.getSearchGames(search)
        return data.map { game ->
            DataMapper.mapListResponsesToListDomain(game)
        }
    }

    override fun getDetailGames(id: Int): Flow<Resource<Games>> =
        object :
            NetworkBoundResource<Games, GamesResponse>() {
            override fun loadFromDB(): Flow<Games> {
                return localDataSource.getDetailGames(id).map {
                    if (it != null) {
                        DataMapper.mapEntitiesToDomain(it)
                    } else it
                }
            }

            override fun shouldFetch(data: Games?): Boolean {
                return data?.id == null
            }

            override suspend fun createCall(): Flow<ApiResponse<GamesResponse>> =
                remoteDataSource.getDetailGames(id)

            override suspend fun saveCallResult(data: GamesResponse) {
                val gamesList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gamesList)
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Games>> =
        localDataSource.getFavoriteGames().map { DataMapper.mapListEntitiesToListDomain(it) }

    override fun setFavoriteGames(games: Games, state: Boolean) {
        val gamesEntity = DataMapper.mapDomainToEntity(games)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGames(gamesEntity, state) }
    }
}