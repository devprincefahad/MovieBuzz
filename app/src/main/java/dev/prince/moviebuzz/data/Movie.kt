package dev.prince.moviebuzz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val backdrop_path: String?,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val vote_average: Double,
    val release_date: String,
    val title: String?
)