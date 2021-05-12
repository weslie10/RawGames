package com.weslie10.rawgames.core.data.source.remote.network

import com.weslie10.rawgames.core.data.source.remote.response.GamesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("/games")
    suspend fun getGames(): GamesResponse

    @GET("/games")
    suspend fun getDetailGames(
        @ParameterName("id") id: Int
    ): GamesResponse
}