package dev.prince.moviebuzz.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.db.MovieDatabase
import dev.prince.moviebuzz.util.TOP_RATED
import dev.prince.moviebuzz.util.UPCOMING
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ApiService,
    db: MovieDatabase
) : ViewModel() {

    private val movieDao = db.movieDao()
    private val genreDao = db.genreDao()

    val topMovies = movieDao.getTopRatedMovies()
    val upcomingMovies = movieDao.getUpcomingMovies()
    val genre = genreDao.getAllGenres()

    val error = MutableLiveData<String>()

    val showProgressBar = MutableLiveData(false)

    init {
        getTopRatedMovies()
        getGenres()
        getUpcomingMovies()
    }

    private fun getTopRatedMovies() {
        showProgressBar.value = true
        viewModelScope.launch {
            try {
                val apiResult = api.getTopRated()
                val movies = apiResult.movies.onEach {
                    it.type = TOP_RATED
                }
                movieDao.insert(movies)
            } catch (e: HttpException) {
                if (movieDao.getTopRatedMoviesSize() == 0) {
                    error.value = "Something went wrong, code: ${e.code()}"
                }
                e.printStackTrace()
            } catch (e: Exception) {
                if (movieDao.getTopRatedMoviesSize() == 0) {
                    error.value = "Something went wrong"
                }
                e.printStackTrace()
            } finally {
                showProgressBar.value = false
            }
        }
    }

    private fun getUpcomingMovies() {
        showProgressBar.value = true
        viewModelScope.launch {
            try {
                val apiResult = api.getUpcoming()
                val movies = apiResult.movies.onEach { it.type = UPCOMING }
                if (movies.isNotEmpty()) {
                    movieDao.clearUpcomingMovies()
                    movieDao.insert(movies)
                }
            } catch (e: HttpException) {
                if (movieDao.getUpcomingMoviesSize() == 0) {
                    error.value = "Something went wrong, code: ${e.code()}"
                }
                e.printStackTrace()
            } catch (e: Exception) {
                if (movieDao.getUpcomingMoviesSize() == 0) {
                    error.value = "Something went wrong"
                }
                e.printStackTrace()
            } finally {
                showProgressBar.value = false
            }
        }
    }

    private fun getGenres() {
        showProgressBar.value = true
        viewModelScope.launch {
            try {
                genreDao.insert(api.getGenres().genres)
            } catch (e: HttpException) {
                if (genreDao.getGenresSize() == 0) {
                    error.value = "Something went wrong, code: ${e.code()}"
                }
                e.printStackTrace()
            } catch (e: Exception) {
                if (genreDao.getGenresSize() == 0) {
                    error.value = "Something went wrong"
                }
                e.printStackTrace()
            } finally {
                showProgressBar.value = false
            }
        }
    }
}