package com.example.musicapp.ui.track

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.track.Data
import com.example.musicapp.model.track.TrackResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(private val repository: TrackRepository) : ViewModel() {

    val trackResponse : MutableLiveData<List<Data>> = MutableLiveData()
    var trackList = arrayListOf<Data>()
    var trackCount : Int = 0

    fun getTracks(id : String,index : Int) = viewModelScope.launch {
        var result = repository.getTracks(id,index)
        trackList.addAll(result.data)
        trackCount = result.total
        while(trackCount - 25 > 0){
            result = repository.getTracks(id,index+25)
            trackList.addAll(result.data)
            trackCount-=25
        }
        trackResponse.value = trackList
    }

}