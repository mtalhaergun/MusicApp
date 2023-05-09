package com.example.musicapp.ui.artist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.artist.ArtistResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistViewModel @Inject constructor(private val repository: ArtistRepository) : ViewModel() {

    val artistResponse : MutableLiveData<ArtistResponse> = MutableLiveData()

    fun getArtist(id : String) = viewModelScope.launch {
        val result = repository.getArtist(id)
        artistResponse.value = result
    }

}