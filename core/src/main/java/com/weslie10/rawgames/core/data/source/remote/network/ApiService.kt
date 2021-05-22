package com.weslie10.rawgames.core.data.source.remote.network

import com.weslie10.rawgames.core.BuildConfig
import com.weslie10.rawgames.core.data.source.remote.response.GamesListResponse
import com.weslie10.rawgames.core.data.source.remote.response.GamesResponse
import com.weslie10.rawgames.core.utils.Utility.decrypt
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") key: String = KEY
    ): GamesListResponse

    @GET("games")
    suspend fun getSearchGames(
        @Query("search") search: String,
        @Query("key") key: String = KEY
    ): GamesListResponse

    @GET("games/{id}")
    suspend fun getDetailGames(
        @Path("id") id: Int,
        @Query("key") key: String = KEY
    ): GamesResponse

    companion object {
        private val KEY = BuildConfig.KEY.decrypt() as String
    }
}