package com.example.musicapp.ui.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.favorite.Favorites
import com.example.musicapp.model.track.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private val repository: FavoritesRepository) : ViewModel() {

    val favorites : MutableLiveData<List<Favorites>> = MutableLiveData()

    fun getFavorites() = viewModelScope.launch {
        val result = repository.getFavorites()
        favorites.value = result
    }

}