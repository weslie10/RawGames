package com.weslie10.rawgames.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.weslie10.rawgames.core.domain.usecase.RawgUseCase

class FavoriteViewModel(rawgUseCase: RawgUseCase): ViewModel() {
    val listFavorite = rawgUseCase.getFavoriteGames().asLiveData()
}