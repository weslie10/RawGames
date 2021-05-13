package com.weslie10.rawgames.core.domain.usecase

import com.weslie10.rawgames.core.data.Resource
import com.weslie10.rawgames.core.domain.model.Games
import kotlinx.coroutines.flow.Flow

interface RawgUseCase {
    fun getAllGames(): Flow<Resource<List<Games>>>
    fun getFavoriteGames(): Flow<List<Games>>
    fun setFavoriteGames(games: Games, state: Boolean)
}