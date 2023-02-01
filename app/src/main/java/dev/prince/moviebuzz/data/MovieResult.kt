package dev.prince.moviebuzz.data

import com.google.gson.annotations.SerializedName

data class MovieResult(
    val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)