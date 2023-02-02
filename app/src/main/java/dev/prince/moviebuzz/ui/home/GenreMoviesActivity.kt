package dev.prince.moviebuzz.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.databinding.ActivityGenreMoviesBinding
import dev.prince.moviebuzz.databinding.ActivityMovieDetailsBinding
import dev.prince.moviebuzz.ui.adapter.GenreMoviesAdapter
import dev.prince.moviebuzz.ui.adapter.TopMoviesAdapter

@AndroidEntryPoint
class GenreMoviesActivity : AppCompatActivity() {

    lateinit var binding: ActivityGenreMoviesBinding
    lateinit var genre_id: String
    lateinit var genre_name: String
    private val viewModel: GenreMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_genre_movies)

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        genre_id = intent.getStringExtra("id").toString()
        genre_name = intent.getStringExtra("name").toString()
        binding.textViewToolbarTitle.text = genre_name

        viewModel.getGenreMoviesList(genre_id.toInt())
        viewModel.genreMovies.observe(this) {
            binding.recyclerGenre.adapter = GenreMoviesAdapter(this, it.movies)
        }

    }
}