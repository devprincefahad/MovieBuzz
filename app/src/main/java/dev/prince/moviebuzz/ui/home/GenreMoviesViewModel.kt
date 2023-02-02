package dev.prince.moviebuzz.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.data.MovieResult
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class GenreMoviesViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {

    val genreMovies = MutableLiveData<MovieResult>()
    val error = MutableLiveData<String>()

    fun getGenreMoviesList(id: Int) {
        viewModelScope.launch {
            try {
                genreMovies.value = api.getMoviesGenre(id)
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