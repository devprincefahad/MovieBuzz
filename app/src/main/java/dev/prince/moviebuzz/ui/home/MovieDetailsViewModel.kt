package dev.prince.moviebuzz.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.moviebuzz.api.ApiService
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.data.MovieResult
import dev.prince.moviebuzz.db.MovieDao
import dev.prince.moviebuzz.db.MovieDatabase
import dev.prince.moviebuzz.repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

    private val repository: Repository
    val isBookmarked = MutableLiveData<Boolean>()

    init {
        val dao = MovieDatabase.getDatabase(application).movieDao()
        repository = Repository(dao)
    }

    fun insertNote(movies: Movie) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(movies)
    }

    fun deleteNote(title: String) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(title)
    }

    fun checkMovie(title: String) {
        viewModelScope.launch {
                isBookmarked.postValue(repository.checkBookmark(title))
        }
    }

}