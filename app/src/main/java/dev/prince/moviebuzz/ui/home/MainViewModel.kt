package dev.prince.moviebuzz.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.data.MovieResult
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    private val top_moviesMLD = MutableLiveData<MovieResult>()
    val topMoviesLD: LiveData<MovieResult> = top_moviesMLD

    fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val result = api.getTopRated()
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        Log.d(
                            TAG,
                            "getTopRatedMovies: Received ${result} " +
                                    "number of movies \n Movies: ${result.body()}"
                        )
                        top_moviesMLD.value = result.body()
                    }
                }

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
}