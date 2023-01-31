package dev.prince.moviebuzz.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import javax.inject.Inject
import kotlinx.coroutines.launch
import retrofit2.HttpException

private const val TAG = "MainViewModel"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                val result = api.getTopRated()
                Log.d(TAG,
                    "getTopRatedMovies: Received ${result.results.size} number of movies \n Movies: ${result.results}")
            } catch (e: HttpException) {
                e.printStackTrace()
                Log.d(TAG,
                    "getTopRatedMovies code: ${e.code()}, message: ${e.message()}")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d(TAG,
                    "getTopRatedMovies: ${e.message}")
            }
        }
    }
}