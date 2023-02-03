package dev.prince.moviebuzz.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.util.BOOKMARKED
import dev.prince.moviebuzz.util.TOP_RATED
import dev.prince.moviebuzz.util.UPCOMING

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>)

    @Query("DELETE FROM movie WHERE id = :id AND type = '$BOOKMARKED'")
    suspend fun deleteMovie(id: Int)

    @Query("SELECT * FROM movie WHERE id = :id AND type = '$BOOKMARKED'")
    fun getMovie(id: Int): LiveData<Movie>

    @Query("SELECT * FROM movie WHERE type = '$BOOKMARKED'")
    fun getBookmarkedMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE type = '$TOP_RATED' ORDER BY vote_average DESC")
    fun getTopRatedMovies(): LiveData<List<Movie>>

    @Query("SELECT * FROM movie WHERE type = '$UPCOMING'")
    fun getUpcomingMovies(): LiveData<List<Movie>>

    @Query("DELETE FROM movie WHERE type = '$UPCOMING'")
    suspend fun clearUpcomingMovies()

    @Query("SELECT COUNT(*) FROM movie WHERE type = '$TOP_RATED'")
    suspend fun getTopRatedMoviesSize(): Int

    @Query("SELECT COUNT(*) FROM movie WHERE type = '$UPCOMING'")
    suspend fun getUpcomingMoviesSize(): Int
}