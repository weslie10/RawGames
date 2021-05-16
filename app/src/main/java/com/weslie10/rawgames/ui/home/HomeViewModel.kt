package com.weslie10.rawgames.ui.home

import androidx.lifecycle.*
import com.weslie10.rawgames.core.domain.usecase.RawgUseCase

class HomeViewModel(rawgUseCase: RawgUseCase) : ViewModel() {
    private val search = MutableLiveData<String>()

    fun setSearch(search: String) {
        this.search.value = search
    }

    fun getSearch(): LiveData<String> = search

    val listGames = rawgUseCase.getAllGames().asLiveData()
    val searchGames = Transformations.switchMap(search) {
        rawgUseCase.getSearchGames(it).asLiveData()
    }
}