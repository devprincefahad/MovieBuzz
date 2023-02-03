package dev.prince.moviebuzz.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.databinding.ActivityMovieDetailsBinding
import dev.prince.moviebuzz.util.getDownloadUrl

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        val movie = intent.getSerializableExtra("movie") as Movie

        binding.textViewTitle.text = movie.original_title
        binding.textViewOverview.text = movie.overview
        Glide.with(this).load(movie.backdrop_path?.getDownloadUrl()).into(binding.detailsImage)
        binding.textViewReleaseDate.text = movie.release_date
        binding.textViewVoteAvg.text = movie.vote_average.toString()
        binding.textViewOriginalLanguage.text = "Language: ${movie.original_language}"

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        viewModel.isBookmarked(movie.id).observe(this) {
            if (it != null) {
                binding.imgBookmark.setImageResource(R.drawable.ic_bookmarked)
                binding.imgBookmark.setOnClickListener {
                    viewModel.removeBookmark(movie)
                }
            } else {
                binding.imgBookmark.setImageResource(R.drawable.ic_bookmark)
                binding.imgBookmark.setOnClickListener {
                    viewModel.addBookmark(movie)
                }
            }
        }

    }
}