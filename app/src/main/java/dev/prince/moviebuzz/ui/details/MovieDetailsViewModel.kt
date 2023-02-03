package dev.prince.moviebuzz.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.db.MovieDatabase
import dev.prince.moviebuzz.util.BOOKMARKED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    db: MovieDatabase
) : ViewModel() {

    private val movieDao = db.movieDao()

    fun isBookmarked(movieId: Int): LiveData<Movie> {
        return movieDao.getMovie(movieId)
    }

    fun removeBookmark(movie: Movie) {
        viewModelScope.launch {
            movieDao.deleteMovie(movie.id)
        }
    }

    fun addBookmark(movie: Movie) {
        viewModelScope.launch {
            movieDao.insert(movie.also { it.type = BOOKMARKED })
        }
    }
}