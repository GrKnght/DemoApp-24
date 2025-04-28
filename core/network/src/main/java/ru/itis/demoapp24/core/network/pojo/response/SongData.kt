package ru.itis.demoapp24.core.network.pojo.response

import com.google.gson.annotations.SerializedName

data class SongData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("artists_names")
    val artist: String?,
    @SerializedName("full_title")
    val fullTitle: String?,
    @SerializedName("header_image_thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("header_image_url")
    val headerImageUrl: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("release-date")
    val releaseDate: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("song_art_primary_color")
    val songArtPrimaryColor: String?,
    @SerializedName("song_art_secondary_color")
    val songArtSecondaryColor: String?
)