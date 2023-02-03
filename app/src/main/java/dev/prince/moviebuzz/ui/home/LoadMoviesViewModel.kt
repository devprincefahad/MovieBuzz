package dev.prince.moviebuzz.ui.home

import android.app.Application
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.data.MovieResult
import dev.prince.moviebuzz.db.MovieDao
import dev.prince.moviebuzz.db.MovieDatabase
import dev.prince.moviebuzz.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class LoadMoviesViewModel @Inject constructor(
    application: Application,
    private val api: ApiService
) : AndroidViewModel(application) {

    private val repository: Repository
    val allNews: LiveData<List<Movie>>

    val genreMovies = MutableLiveData<MovieResult>()
    val error = MutableLiveData<String>()

    init {
        val dao = MovieDatabase.getDatabase(application).movieDao()
        repository = Repository(dao)
        allNews = repository.allNews
    }

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