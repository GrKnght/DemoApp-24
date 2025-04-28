package ru.itis.demoapp24.core.network.pojo.response

import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("result")
    val result: SearchResult?
)

data class SearchResult(
    @SerializedName("id")
    val songId: Int?,
    @SerializedName("artist_names")
    val artistName: String?,
    @SerializedName("song_art_image_thumbnail_url")
    val songArtThumbnail: String?
)