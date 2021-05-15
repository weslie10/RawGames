package com.weslie10.rawgames.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.weslie10.rawgames.core.domain.model.Games
import com.weslie10.rawgames.core.domain.usecase.RawgUseCase

class DetailViewModel(private val rawgUseCase: RawgUseCase) : ViewModel() {
    private val id = MutableLiveData<Int>()

    fun setId(id: Int) {
        this.id.value = id
    }

    val game = Transformations.switchMap(id) { id ->
        rawgUseCase.getDetailGames(id).asLiveData()
    }

    fun setFavoriteGames(games: Games) = rawgUseCase.setFavoriteGames(games)
}