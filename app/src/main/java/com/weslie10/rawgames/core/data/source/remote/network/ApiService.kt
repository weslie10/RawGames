package com.weslie10.rawgames.core.data.source.remote.network

import com.weslie10.rawgames.core.data.source.remote.response.GamesListResponse
import com.weslie10.rawgames.core.data.source.remote.response.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/games")
    suspend fun getGames(
        @Query("key") key: String = KEY
    ): GamesListResponse

    @GET("/games")
    suspend fun searchGames(
        @Query("key") key: String = KEY
    ): GamesListResponse

    @GET("/games/{id}")
    suspend fun getDetailGames(
        @Query("key") key: String = KEY,
        @Path("id") id: Int
    ): GamesResponse

    companion object {
        private const val KEY = "1b43eaf726e14fd78bba9f06f34332b0"
    }
}