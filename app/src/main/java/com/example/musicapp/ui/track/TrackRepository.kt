package com.example.musicapp.ui.track

import com.example.musicapp.model.track.TrackResponse
import com.example.musicapp.network.ApiFactory
import javax.inject.Inject

class TrackRepository @Inject constructor(private val apiFactory: ApiFactory) {

    suspend fun getTracks(id : String, index : Int) : TrackResponse{
        return apiFactory.getTracks(id,index)
    }

}