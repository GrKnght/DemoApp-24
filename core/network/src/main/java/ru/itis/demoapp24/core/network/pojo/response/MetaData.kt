package ru.itis.demoapp24.core.network.pojo.response

import com.google.gson.annotations.SerializedName

data class MetaData(
    @SerializedName("status")
    val status: Int,
    @SerializedName("message")
    val message: String?
)