package com.example.musicapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.musicapp.model.favorite.Favorites
import com.example.musicapp.model.track.Data

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    suspend fun getFavorites() : List<Favorites>

    @Insert
    suspend fun addFavorites(favorite : Favorites)
}