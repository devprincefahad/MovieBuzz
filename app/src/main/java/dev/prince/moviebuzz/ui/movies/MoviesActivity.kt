package dev.prince.moviebuzz.ui.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.moviebuzz.databinding.ActivityLoadMoviesBinding
import dev.prince.moviebuzz.ui.adapter.MoviesAdapter

@AndroidEntryPoint
class MoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadMoviesBinding
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        if (intent.hasExtra("id")) {
            val genreId = intent.getIntExtra("id", 0)
            getMovieForGenre(genreId)
        } else {
            binding.textViewToolbarTitle.text = "Bookmarked Movies"
            loadBookmarkedMovies()
        }

        viewModel.showProgressBar.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) {
            if (it.isNotEmpty()) {
                Snackbar.make(
                    binding.root, it, Snackbar.LENGTH_SHORT
                ).show()
                viewModel.error.value = ""
            }
        }
    }

    private fun loadBookmarkedMovies() {
        viewModel.bookmarkedMovies.observe(this) {
            binding.listMovies.adapter = MoviesAdapter(this, it)

            if (it.isEmpty()) {
                binding.textViewNoMovies.visibility = View.VISIBLE
            }
        }
    }

    private fun getMovieForGenre(genreId: Int) {
        viewModel.genreMovies.observe(this) {
            binding.listMovies.adapter = MoviesAdapter(this, it.movies)
        }
        viewModel.genreName.observe(this) {
            binding.textViewToolbarTitle.text = it
        }

        viewModel.getMoviesForGenre(genreId)
    }

}