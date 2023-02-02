package dev.prince.moviebuzz.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.databinding.ActivityGenreMoviesBinding
import dev.prince.moviebuzz.databinding.ActivityMovieDetailsBinding
import dev.prince.moviebuzz.ui.adapter.GenreMoviesAdapter
import dev.prince.moviebuzz.ui.adapter.TopMoviesAdapter

@AndroidEntryPoint
class GenreMoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityGenreMoviesBinding
    private val viewModel: GenreMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_genre_movies)

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        val genreId = intent.getIntExtra("id", -1)
        val genreName = intent.getStringExtra("name").toString()
        binding.textViewToolbarTitle.text = genreName

        viewModel.getGenreMoviesList(genreId)
        viewModel.genreMovies.observe(this) {
            binding.recyclerGenre.adapter = GenreMoviesAdapter(this, it.movies)
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
}