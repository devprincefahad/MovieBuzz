package dev.prince.moviebuzz.ui.movies

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.data.MovieResult
import dev.prince.moviebuzz.db.MovieDatabase
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val api: ApiService,
    private val db: MovieDatabase
) : ViewModel() {

    val genreMovies = MutableLiveData<MovieResult>()
    val error = MutableLiveData<String>()
    val showProgressBar = MutableLiveData(false)

    val bookmarkedMovies = db.movieDao().getBookmarkedMovies()
    val genreName = MutableLiveData<String>()

    fun getMoviesForGenre(id: Int) {
        showProgressBar.value = true
        viewModelScope.launch {
            genreName.value = db.genreDao().getGenre(id)?.name
            try {
                genreMovies.value = api.getMoviesGenre(id)
            } catch (e: HttpException) {
                error.value = "Something went wrong, code: ${e.code()}"
                e.printStackTrace()
            } catch (e: Exception) {
                error.value = "Something went wrong"
                e.printStackTrace()
            } finally {
                showProgressBar.value = false
            }
        }
    }

}