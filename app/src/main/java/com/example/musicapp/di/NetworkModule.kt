package com.example.musicapp.di

import android.content.Context
import androidx.room.Room
import com.example.musicapp.network.ApiFactory
import com.example.musicapp.room.DatabaseFavorites
import com.example.musicapp.room.FavoritesDao
import com.example.musicapp.ui.favorites.FavoritesRepository
import com.example.musicapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ) : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient).addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun provideApiFactory(retrofit: Retrofit): ApiFactory {
        return retrofit.create(ApiFactory::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoritesDaoRepository(fdao : FavoritesDao) : FavoritesRepository {
        return FavoritesRepository(fdao)
    }

    @Provides
    @Singleton
    fun provideKisilerDao(@ApplicationContext context: Context) : FavoritesDao{
        val vt = Room.databaseBuilder(context,DatabaseFavorites::class.java,"favorite.sqlite")
            .build()
        return vt.getFavoritesDao()
    }

}