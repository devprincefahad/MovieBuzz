package dev.prince.moviebuzz.data

data class
MovieResult(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)