package dev.prince.moviebuzz.util

fun String.getDownloadUrl(): String {
    return "https://image.tmdb.org/t/p/original/$this"
}