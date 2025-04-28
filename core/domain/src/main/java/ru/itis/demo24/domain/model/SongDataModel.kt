package ru.itis.demo24.domain.model

data class SongDataModel(
    val title: String,
) {
    companion object {
        val EMPTY = SongDataModel(
            title = ""
        )
    }
}
