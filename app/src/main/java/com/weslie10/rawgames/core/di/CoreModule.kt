package com.weslie10.rawgames.core.di

import androidx.room.Room
import com.weslie10.rawgames.core.data.RawgRepository
import com.weslie10.rawgames.core.data.source.local.LocalDataSource
import com.weslie10.rawgames.core.data.source.local.room.RawgDatabase
import com.weslie10.rawgames.core.data.source.remote.RemoteDataSource
import com.weslie10.rawgames.core.data.source.remote.network.ApiService
import com.weslie10.rawgames.core.domain.repository.IRawgRepository
import com.weslie10.rawgames.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory {
        get<RawgDatabase>().rawgDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            RawgDatabase::class.java, "Rawg.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRawgRepository> { RawgRepository(get(), get(), get()) }
}