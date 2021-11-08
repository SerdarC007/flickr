package com.example.searchapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapp.data.Photo
import com.example.searchapp.networking.WebClient
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {


    suspend fun fetchImages(searchTerm: String): List<Photo> {
        if (searchTerm.isBlank()) {
            return emptyList()
        }
        val searchResponse = WebClient.client.fetchImages(searchTerm)
        return searchResponse.photos.photo.map { photo ->
            Photo(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title)

            }
    }
}