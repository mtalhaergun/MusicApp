package com.example.musicapp.ui.genre

import com.example.musicapp.model.genre.GenreResponse
import com.example.musicapp.network.ApiFactory
import javax.inject.Inject

class GenreRepository @Inject constructor(private val apiFactory : ApiFactory) {

    suspend fun getGenres() : GenreResponse{
        return apiFactory.getGenres()
    }

}