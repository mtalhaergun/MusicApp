package com.example.musicapp.ui.artistdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.album.AlbumResponse
import com.example.musicapp.model.album.Data
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(private val repository: ArtistDetailRepository) : ViewModel() {

    val albumResponse : MutableLiveData<List<Data>> = MutableLiveData()
    var albumList = arrayListOf<Data>()
    var albumCount : Int = 0

    fun getAlbums(id : String, index : Int) = viewModelScope.launch {
        var result = repository.getAlbums(id,index)
        albumList.addAll(result.data)
        albumCount = result.total
        while(albumCount - 25 > 0){
            result = repository.getAlbums(id,index+25)
            albumList.addAll(result.data)
            albumCount-=25
        }
        albumResponse.value = albumList
    }

}