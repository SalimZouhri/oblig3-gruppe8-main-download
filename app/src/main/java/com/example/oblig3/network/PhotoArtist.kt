package com.example.oblig3.network

import com.squareup.moshi.Json

class PhotoArtist (
    @Json(name = "id") val id: Int=-1,
    @Json(name = "name") val name: String="undefined",
    @Json(name = "email") val email: String="undefined",
    @Json(name = "phone") val phone: String="undefined",
    @Json(name = "website") val website: String="undefined",
)