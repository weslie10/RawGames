package com.weslie10.rawgames.di

import com.weslie10.rawgames.core.domain.usecase.RawgUseCase
import com.weslie10.rawgames.core.domain.usecase.RawgInteractor
import com.weslie10.rawgames.detail.DetailViewModel
import com.weslie10.rawgames.favorite.FavoriteViewModel
import com.weslie10.rawgames.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<RawgUseCase> { RawgInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}