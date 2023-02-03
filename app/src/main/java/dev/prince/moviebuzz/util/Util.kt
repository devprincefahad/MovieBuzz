package dev.prince.moviebuzz.util

fun String.getDownloadUrl(): String {
    return "https://image.tmdb.org/t/p/original/$this"
}

const val TOP_RATED = "top_rated"
const val UPCOMING = "upcoming"
const val BOOKMARKED = "bookmarked"