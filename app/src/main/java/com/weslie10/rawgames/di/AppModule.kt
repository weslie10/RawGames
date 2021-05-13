package com.weslie10.rawgames.di

import com.weslie10.rawgames.core.domain.usecase.RawgUseCase
import com.weslie10.rawgames.core.domain.usecase.RawgInteractor
import org.koin.dsl.module

val useCaseModule = module {
    factory<RawgUseCase> { RawgInteractor(get()) }
}

val viewModelModule = module {
//    viewModel { HomeViewModel(get()) }
//    viewModel { FavoriteViewModel(get()) }
//    viewModel { DetailRawgViewModel(get()) }
}