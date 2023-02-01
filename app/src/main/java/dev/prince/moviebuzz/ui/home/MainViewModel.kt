package dev.prince.moviebuzz.ui.home

import android.util.Log
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

    val topMovies = MutableLiveData<MovieResult>()

    fun getTopRatedMovies() {
        viewModelScope.launch {
            try {
                topMovies.value = api.getTopRated()
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