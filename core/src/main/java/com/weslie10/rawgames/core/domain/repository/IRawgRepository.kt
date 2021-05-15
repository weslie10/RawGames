package com.weslie10.rawgames.core.domain.repository

import com.weslie10.rawgames.core.data.Resource
import com.weslie10.rawgames.core.data.source.remote.response.ResultsItem
import com.weslie10.rawgames.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface IRawgRepository {
    fun getAllGames(): Flow<List<ResultsItem>>
    fun getSearchGames(search: String): Flow<List<ResultsItem>>
    fun getDetailGames(id: Int): Flow<Resource<Games>>

    fun getFavoriteGames(): Flow<List<Games>>
    fun setFavoriteGames(games: Games, state: Boolean)
}