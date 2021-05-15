package com.weslie10.rawgames.core.data.source.remote

import android.util.Log
import com.weslie10.rawgames.core.data.source.remote.network.ApiResponse
import com.weslie10.rawgames.core.data.source.remote.network.ApiService
import com.weslie10.rawgames.core.data.source.remote.response.GamesResponse
import com.weslie10.rawgames.core.data.source.remote.response.ResultsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    fun getAllGames(): Flow<List<ResultsItem>> {
        return flow {
            try {
                emit(apiService.getGames().results)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getSearchGames(search: String): Flow<List<ResultsItem>> {
        return flow {
            try {
                emit(apiService.getSearchGames(search).results)
            } catch (e: Exception) {
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailGames(id: Int): Flow<ApiResponse<GamesResponse>> {
        return flow {
            try {
                val response = apiService.getDetailGames(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
