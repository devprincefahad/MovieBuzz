package dev.prince.moviebuzz.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.data.Genre
import dev.prince.moviebuzz.data.MovieResult
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    val topMovies = MutableLiveData<MovieResult>()
    val upcomingMovies = MutableLiveData<MovieResult>()
    val genre = MutableLiveData<Genre>()

    fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                topMovies.value = api.getTopRated()
                //Log.d(TAG, topMovies.value.toString())
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.d(
                    TAG,
                    "getTopRatedMovies code: ${e.code()}, message: ${e.message()}"
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(
                    TAG,
                    "getTopRatedMovies: ${e.message}"
                )
            }
        }
    }

    fun getUpcomingMovies() {
        viewModelScope.launch {
            try {
                upcomingMovies.value = api.getUpcoming()
                //Log.d(TAG, popularMovies.value.toString())
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.d(
                    TAG,
                    "getLatestMovies code: ${e.code()}, message: ${e.message()}"
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(
                    TAG,
                    "getLatestMovies: ${e.message}"
                )
            }
        }
    }

    fun getGenres() {
        viewModelScope.launch {
            try {
                genre.value = api.getGenres()
                Log.d(TAG, genre.value.toString())
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.d(
                    TAG,
                    "getGenres code: ${e.code()}, message: ${e.message()}"
                )
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(
                    TAG,
                    "getGenres: ${e.message}"
                )
            }
        }
    }

}