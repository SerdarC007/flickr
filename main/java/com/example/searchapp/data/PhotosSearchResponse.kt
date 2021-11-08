package com.example.searchapp.data

// add the data layer to the data classes for the API Response
data class PhotosSearchResponse(
    val photos: PhotosMetaData


)
data class PhotosMetaData(
    val page: Int,
    val photo: List<PhotoResponse>
)

data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String
)
