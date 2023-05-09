package com.example.musicapp.model.album

data class AlbumResponse(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)