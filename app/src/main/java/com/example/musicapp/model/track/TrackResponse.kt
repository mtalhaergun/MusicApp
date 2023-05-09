package com.example.musicapp.model.track

data class TrackResponse(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)