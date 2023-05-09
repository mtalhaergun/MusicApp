package com.example.musicapp.ui.artist

import com.example.musicapp.model.artist.ArtistResponse
import com.example.musicapp.network.ApiFactory
import javax.inject.Inject

class ArtistRepository @Inject constructor(private val apiFactory: ApiFactory) {

    suspend fun getArtist(id : String) : ArtistResponse{
        return apiFactory.getArtists(id)
    }

}