package dev.prince.moviebuzz.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    val name: String,
    @PrimaryKey val id: Int
)
