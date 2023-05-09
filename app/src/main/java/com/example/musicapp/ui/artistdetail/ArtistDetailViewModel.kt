package com.example.musicapp.ui.artistdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.album.AlbumResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(private val repository: ArtistDetailRepository) : ViewModel() {

    val albumResponse : MutableLiveData<AlbumResponse> = MutableLiveData()

    fun getAlbums(id : String) = viewModelScope.launch {
        val result = repository.getAlbums(id)
        albumResponse.value = result
    }

}