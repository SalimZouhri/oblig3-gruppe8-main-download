package com.example.oblig3.network

import com.squareup.moshi.Json

class PhotoAlbum (
    @Json(name = "userId") val userId: Int = -1,
    @Json(name = "id") val id: Int = -1,
    @Json(name = "title") val title: String = "undefined",
)