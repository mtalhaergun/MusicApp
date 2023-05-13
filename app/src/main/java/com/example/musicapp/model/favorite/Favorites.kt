package com.example.musicapp.model.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.musicapp.model.track.Artist
import java.io.Serializable

@Entity(tableName = "favorites")
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "duration")
    val duration: Int,
    @ColumnInfo(name = "md5_image")
    val md5_image: String,
    @ColumnInfo(name = "preview")
    val preview: String,
    @ColumnInfo(name = "title")
    val title: String

) : Serializable