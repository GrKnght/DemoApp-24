package ru.itis.demo24.domain.model

data class DogFactsModel(
    val header: String,
    val descr: String,
) {
    companion object {
        val EMPTY = DogFactsModel(
            header = "",
            descr = ""
        )
    }
}
