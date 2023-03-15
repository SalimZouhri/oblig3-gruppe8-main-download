package com.example.oblig3.network

import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Path

private const val BASE_URL =
    "https://jsonplaceholder.typicode.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PictureApiService {
    @GET("photos")
    suspend fun getPhotos(): List<PhotoImage>

    @GET("albums/{id}")
    suspend fun getAlbum(@Path("id") id: Int): PhotoAlbum

    @GET("users/{id}")
    suspend fun getArtist(@Path("id") id: Int): PhotoArtist
}

object PictureApi {
    val retrofitService : PictureApiService by lazy {
        retrofit.create(PictureApiService::class.java) }

    //TODO(retrofit service for "albums" og "users")
}