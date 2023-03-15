package com.example.oblig3.network

import com.squareup.moshi.Json

//Objekt som holder info fra ".../photos" nettsiden
data class PhotoImage (
    @Json(name = "albumId") val albumId: Int=-1,
    @Json(name = "id") val id: Int=-1,
    @Json(name = "title") val title: String="undefined",
    @Json(name = "url") val url: String="undefined",
    @Json(name = "thumbnailUrl") val thumbnailUrl: String="undefined",
        )
