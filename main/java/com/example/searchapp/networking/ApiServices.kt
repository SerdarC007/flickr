package com.example.searchapp.networking

import com.example.searchapp.BuildConfig
import com.example.searchapp.BuildConfig.FLICKR_API_KEY
import com.example.searchapp.data.PhotosSearchResponse
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

//use retrofit & Okhttp to fetch the images
//and use picasso to draw them in RecyclerView

// Retrofit's base urls must end with a "/"
private const val BASE_URL = "https://api.flickr.com/services/rest/"
private const val CONNECTION_TIMEOUT_MS: Long = 10

object WebClient {
    val client: ApiServices by lazy {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
                )
            )
                //this can also be done with dagger, by using provide annotation
            .client(
                OkHttpClient.Builder().connectTimeout(
                    CONNECTION_TIMEOUT_MS,
                    TimeUnit.SECONDS
                ).addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create(ApiServices::class.java)
    }
}

interface ApiServices {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=" + BuildConfig.FLICKR_API_KEY)
    suspend fun fetchImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse
}
//Suspend function is for handling threading with coruntines, it can be paused and resumed later on without blocking
//API key is in the build config file, for good practise