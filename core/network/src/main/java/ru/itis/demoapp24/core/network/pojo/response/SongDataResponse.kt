package ru.itis.demoapp24.core.network.pojo.response

import com.google.gson.annotations.SerializedName

data class SongDataResponse(
    @SerializedName("meta")
    val meta: MetaData?,
    @SerializedName("response")
    val response: ApiResponse?,
)

data class ApiResponse(
    @SerializedName("song")
    val song: SongData?,
    @SerializedName("hits")
    val hits: List<SearchData>?,
)