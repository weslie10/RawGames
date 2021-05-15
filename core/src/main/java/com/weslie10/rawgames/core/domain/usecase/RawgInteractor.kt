package com.weslie10.rawgames.core.domain.usecase

import com.weslie10.rawgames.core.data.Resource
import com.weslie10.rawgames.core.data.source.remote.response.ResultsItem
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.domain.repository.IRawgRepository
import kotlinx.coroutines.flow.Flow

class RawgInteractor(private val repository: IRawgRepository) : RawgUseCase {
    override fun getAllGames(): Flow<List<ResultsItem>> = repository.getAllGames()

    override fun getSearchGames(search: String): Flow<List<ResultsItem>> =
        repository.getSearchGames(search)

    override fun getDetailGames(id: Int): Flow<Resource<Games>> = repository.getDetailGames(id)

    override fun getFavoriteGames(): Flow<List<Games>> = repository.getFavoriteGames()

    override fun setFavoriteGames(games: Games, state: Boolean) =
        repository.setFavoriteGames(games, state)
}