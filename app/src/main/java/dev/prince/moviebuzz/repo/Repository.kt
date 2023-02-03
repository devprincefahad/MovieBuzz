package dev.prince.moviebuzz.repo

import androidx.lifecycle.LiveData
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.db.MovieDao

class Repository(private val moviesDao: MovieDao) {

    val allNews: LiveData<List<Movie>> = moviesDao.getAllDbMovie()

    suspend fun insert(movie: Movie) {
        moviesDao.insert(movie)
    }

    suspend fun delete(title: String) {
        moviesDao.delete(title)
    }

    fun checkBookmark(title: String): Boolean {
        val list = moviesDao.checkBookmark(title)
        if (!list.isNullOrEmpty()){
            return true
        }
        return false
    }


}