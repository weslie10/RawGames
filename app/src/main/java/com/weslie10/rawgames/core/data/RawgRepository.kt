package com.weslie10.rawgames.core.data

import com.weslie10.rawgames.core.data.source.local.LocalDataSource
import com.weslie10.rawgames.core.data.source.remote.RemoteDataSource
import com.weslie10.rawgames.core.data.source.remote.network.ApiResponse
import com.weslie10.rawgames.core.data.source.remote.response.GamesResponse
import com.weslie10.rawgames.core.data.source.remote.response.ResultsItem
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

    companion object {
        @Volatile
        private var instance: RawgRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): RawgRepository =
            instance ?: synchronized(this) {
                instance ?: RawgRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getAllGames(): Flow<Resource<List<Games>>> =
        object :
            NetworkBoundResource<List<Games>, List<ResultsItem>>() {
            override fun loadFromDB(): Flow<List<Games>> {
                return localDataSource.getAllGames().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<Games>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItem>>> =
                remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GamesResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(tourismList)
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Games>> {
        return localDataSource.getFavoriteGames().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun setFavoriteGames(tourism: Games, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(tourism)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGames(tourismEntity, state) }
    }
}

