package com.example.musicapp.network

import com.example.musicapp.model.album.AlbumResponse
import com.example.musicapp.model.artist.ArtistResponse
import com.example.musicapp.model.artistdetail.ArtistDetailResponse
import com.example.musicapp.model.genre.GenreResponse
import com.example.musicapp.model.track.TrackResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiFactory {

    @GET("genre")
    suspend fun getGenres() : GenreResponse

    @GET("genre/{id}/artists")
    suspend fun getArtists(
        @Path("id") id : String
    ) : ArtistResponse

    @GET("artist/{id}")
    suspend fun getArtistDetail(
        @Path("id") id : String
    ) : ArtistDetailResponse

    @GET("artist/{id}/albums")
    suspend fun getAlbums(
        @Path("id") id : String
    ) : AlbumResponse

    @GET("album/{id}/tracks")
    suspend fun getTracks(
        @Path("id") id : String
    ) : TrackResponse

}