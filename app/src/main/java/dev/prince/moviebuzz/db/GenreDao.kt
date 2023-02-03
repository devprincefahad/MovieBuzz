package dev.prince.moviebuzz.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.prince.moviebuzz.data.Genre

@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(genres: List<Genre>)

    @Query("SELECT * FROM genre")
    fun getAllGenres(): LiveData<List<Genre>>

    @Query("SELECT * FROM genre WHERE id = :id")
    suspend fun getGenre(id: Int): Genre?

    @Query("SELECT COUNT(*) FROM genre")
    suspend fun getGenresSize(): Int
}