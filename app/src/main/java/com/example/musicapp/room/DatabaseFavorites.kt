package com.example.musicapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.musicapp.model.favorite.Favorites
import com.example.musicapp.model.track.Data

@Database(entities = [Favorites::class], version = 1)
abstract class DatabaseFavorites : RoomDatabase() {
    abstract fun getFavoritesDao() : FavoritesDao
}