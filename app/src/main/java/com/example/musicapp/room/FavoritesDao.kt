package com.example.musicapp.room

import androidx.room.Dao
import androidx.room.Delete
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

    @Delete
    suspend fun deleteFavorites(favorite: Favorites)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE id = :id LIMIT 1)")
    suspend fun searchId(id: Long): Boolean
}