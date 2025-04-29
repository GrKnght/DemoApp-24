package ru.itis.demo24.domain.model

data class SearchResultModel(
    val id: Int,
    val title: String,
    val artist: String,
    val thumbnailUrl: String,
) {
    companion object {
        val EMPTY = SearchResultModel(
            id = -1,
            title = "",
            artist = "",
            thumbnailUrl = ""
        )
    }
}
