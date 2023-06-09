package com.example.musicapp.model.album

import java.io.Serializable

data class Data(

    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val fans: Int,
    val genre_id: Int,
    val id: Long,
    val link: String,
    val md5_image: String,
    val record_type: String,
    val release_date: String,
    val title: String,
    val tracklist: String,
    val type: String
) : Serializable