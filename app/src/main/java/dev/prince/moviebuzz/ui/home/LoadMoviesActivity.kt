package dev.prince.moviebuzz.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.databinding.ActivityLoadMoviesBinding
import dev.prince.moviebuzz.db.MovieDatabase
import dev.prince.moviebuzz.ui.adapter.GenreMoviesAdapter

@AndroidEntryPoint
class LoadMoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoadMoviesBinding
    private val viewModel: LoadMoviesViewModel by viewModels()
    private lateinit var database: MovieDatabase
    var nList = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_load_movies)

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        val genreId = intent.getIntExtra("id", -1)
        val genreName = intent.getStringExtra("name")

        if (!genreName.isNullOrEmpty()) {
            binding.textViewToolbarTitle.text = genreName
            getGenreMoviesList(genreId)
        } else {
            binding.textViewToolbarTitle.text = "Saved News"
            loadSavedDB()
        }

        viewModel.error.observe(this) {
            if (it.isNotEmpty()) {
                Snackbar.make(
                    binding.root,
                    it,
                    Snackbar.LENGTH_SHORT
                ).show()
                viewModel.error.value = ""
            }
        }
    }

    private fun loadSavedDB() {
        viewModel.allNews.observe(this, Observer {
            for (i in it) {
                nList.add(
                    Movie(
                        i.id,
                        i.backdrop_path,
                        i.original_language,
                        i.original_title,
                        i.overview,
                        i.vote_average,
                        i.release_date,
                        i.title
                    )
                )
            }
            Log.d("data", nList.toString())
            if (nList.isNullOrEmpty()) {
                Toast.makeText(this, "No data saved", Toast.LENGTH_SHORT).show()
            }
            viewModel.genreMovies.observe(this) {
                binding.recyclerGenre.adapter = GenreMoviesAdapter(this,nList)
            }
        })
        database = MovieDatabase.getDatabase(this)
    }

    private fun getGenreMoviesList(genreId: Int) {
        viewModel.getGenreMoviesList(genreId)
        viewModel.genreMovies.observe(this) {
            binding.recyclerGenre.adapter = GenreMoviesAdapter(this, it.movies)
        }
    }

}