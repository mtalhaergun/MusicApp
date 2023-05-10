package com.example.musicapp.ui.track

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musicapp.model.track.TrackResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackViewModel @Inject constructor(private val repository: TrackRepository) : ViewModel() {

    val trackResponse : MutableLiveData<TrackResponse> = MutableLiveData()

    fun getTracks(id : String) = viewModelScope.launch {
        val result = repository.getTracks(id)
        trackResponse.value = result
    }

}