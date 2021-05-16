package com.weslie10.rawgames.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.domain.usecase.RawgUseCase

class FavoriteViewModel(private val rawgUseCase: RawgUseCase) : ViewModel() {
    val listFavorite = rawgUseCase.getFavoriteGames().asLiveData()

    fun setFavoriteGames(games: Games, state: Boolean) = rawgUseCase.setFavoriteGames(games, state)
}