package dev.prince.moviebuzz.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.databinding.ActivityMovieDetailsBinding
import dev.prince.moviebuzz.util.getDownloadUrl

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    lateinit var original_title: String
    lateinit var overview: String
    lateinit var backdrop_path: String
    lateinit var release_date: String
    lateinit var original_language: String
    lateinit var vote_average: String
    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        getIntentValues()
        checkBookmark()
        setUIvalues()

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        binding.imgBookmark.setOnClickListener {
            val bookMark =
                Movie(
                    0,
                    backdrop_path,
                    original_language,
                    original_title,
                    overview,
                    vote_average.toDouble(),
                    release_date,
                    null
                )

            viewModel.insertNote(bookMark)
            binding.imgBookmark.visibility = View.GONE
            binding.imgBookmarkEd.visibility = View.VISIBLE
        }

        binding.imgBookmarkEd.setOnClickListener {
            viewModel.deleteNote(original_title)
            binding.imgBookmarkEd.visibility = View.GONE
            binding.imgBookmark.visibility = View.VISIBLE
        }

    }

    private fun setUIvalues() {
        binding.textViewTitle.text = original_title
        binding.textViewOverview.text = overview
        Glide.with(this).load(backdrop_path.getDownloadUrl()).into(binding.detailsImage)
        binding.textViewReleaseDate.text = release_date
        binding.textViewVoteAvg.text = vote_average
        binding.textViewOriginalLanguage.text = "Language: $original_language"
    }

    private fun getIntentValues() {
        original_title = intent.getStringExtra("original_title").toString()
        overview = intent.getStringExtra("overview").toString()
        backdrop_path = intent.getStringExtra("backdrop_path").toString()
        release_date = intent.getStringExtra("release_date").toString()
        original_language = intent.getStringExtra("original_language").toString()
        vote_average = intent.getStringExtra("vote_average").toString()
    }

    private fun checkBookmark() {
        viewModel.checkMovie(original_title)
        viewModel.isBookmarked.observe(this, Observer {
            if (it) {
                binding.imgBookmark.visibility = View.GONE
                binding.imgBookmarkEd.visibility = View.VISIBLE
            } else {
                binding.imgBookmark.visibility = View.VISIBLE
                binding.imgBookmarkEd.visibility = View.GONE
            }
        })
    }
}