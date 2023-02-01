package dev.prince.moviebuzz.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.data.GenreResponse
import dev.prince.moviebuzz.data.MovieResult
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    val topMovies = MutableLiveData<MovieResult>()
    val upcomingMovies = MutableLiveData<MovieResult>()
    val genre = MutableLiveData<GenreResponse>()

    val error = MutableLiveData<String>()

    init {
        getTopRatedMovies()
        getGenres()
        getUpcomingMovies()
    }

    private fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                topMovies.value = api.getTopRated()
            } catch (e: HttpException) {
                error.value = "Something went wrong, code: ${e.code()}"
                e.printStackTrace()
            } catch (e: Exception) {
                error.value = "Something went wrong"
                e.printStackTrace()
            }
        }
    }

    private fun getUpcomingMovies() {
        viewModelScope.launch {
            try {
                upcomingMovies.value = api.getUpcoming()
            } catch (e: HttpException) {
                error.value = "Something went wrong, code: ${e.code()}"
                e.printStackTrace()
            } catch (e: Exception) {
                error.value = "Something went wrong"
                e.printStackTrace()
            }
        }
    }

    private fun getGenres() {
        viewModelScope.launch {
            try {
                genre.value = api.getGenres()
            } catch (e: HttpException) {
                error.value = "Something went wrong, code: ${e.code()}"
                e.printStackTrace()
            } catch (e: Exception) {
                error.value = "Something went wrong"
                e.printStackTrace()
            }
        }
    }
}