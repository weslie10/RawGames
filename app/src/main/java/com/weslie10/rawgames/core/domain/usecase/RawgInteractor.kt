package com.weslie10.rawgames.core.domain.usecase

import com.weslie10.rawgames.core.data.Resource
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.domain.repository.IRawgRepository
import kotlinx.coroutines.flow.Flow

class RawgInteractor(private val repository: IRawgRepository) : RawgUseCase {
    override fun getAllGames(): Flow<Resource<List<Games>>> = repository.getAllGames()

    override fun getFavoriteGames(): Flow<List<Games>> = repository.getFavoriteGames()

    override fun setFavoriteGames(games: Games, state: Boolean) =
        repository.setFavoriteGames(games, state)
}