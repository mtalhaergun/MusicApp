package com.example.musicapp.ui.favorites

import com.example.musicapp.model.favorite.Favorites
import com.example.musicapp.model.track.Data
import com.example.musicapp.room.FavoritesDao
import javax.inject.Inject

class FavoritesRepository @Inject constructor (var fdao : FavoritesDao) {

    suspend fun getFavorites() : List<Favorites>{
        return fdao.getFavorites()
    }

}