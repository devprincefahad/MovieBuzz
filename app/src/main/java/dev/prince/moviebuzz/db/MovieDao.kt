package dev.prince.moviebuzz.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.prince.moviebuzz.data.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: Movie)

    @Query("DELETE FROM movie WHERE original_title=:title")
    suspend fun delete(title: String)

    @Query("SELECT * from movie")
    fun getAllDbMovie(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE original_title=:title")
    fun checkBookmark(title: String): List<Movie>

}