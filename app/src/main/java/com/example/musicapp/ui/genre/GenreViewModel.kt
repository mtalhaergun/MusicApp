package com.example.musicapp.ui.genre

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.genre.GenreResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(private val repository: GenreRepository) : ViewModel() {

    val genreResponse : MutableLiveData<GenreResponse> = MutableLiveData()

    fun getGenres() = viewModelScope.launch {
        val result = repository.getGenres()
        genreResponse.value = result
    }
}