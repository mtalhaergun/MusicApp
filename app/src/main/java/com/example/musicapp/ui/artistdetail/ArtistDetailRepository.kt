package com.example.musicapp.ui.artistdetail

import com.example.musicapp.model.album.AlbumResponse
import com.example.musicapp.network.ApiFactory
import javax.inject.Inject

class ArtistDetailRepository @Inject constructor(private val apiFactory: ApiFactory) {

    suspend fun getAlbums(id : String) : AlbumResponse{
        return apiFactory.getAlbums(id)
    }

}