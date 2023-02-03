package dev.prince.moviebuzz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.prince.moviebuzz.data.Genre
import dev.prince.moviebuzz.data.Movie

@Database(entities = [Movie::class, Genre::class], version = 1)
abstract class MovieDatabase  : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun genreDao(): GenreDao
}