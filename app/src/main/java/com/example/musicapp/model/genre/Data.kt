package com.example.musicapp.model.genre

import java.io.Serializable

data class Data(

    val id: Long,
    val name: String,
    val picture: String,
    val picture_big: String,
    val picture_medium: String,
    val picture_small: String,
    val picture_xl: String,
    val type: String
) : Serializable