package dev.prince.moviebuzz.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.databinding.ActivityMainBinding
import dev.prince.moviebuzz.databinding.ActivityMovieDetailsBinding
import dev.prince.moviebuzz.util.getDownloadUrl
import kotlin.properties.Delegates

class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    lateinit var original_title: String
    lateinit var overview: String
    lateinit var backdrop_path: String
    lateinit var poster_path: String
    lateinit var release_date: String
    lateinit var original_language: String
    lateinit var vote_average: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        original_title = intent.getStringExtra("original_title").toString()
        overview = intent.getStringExtra("overview").toString()
        backdrop_path = intent.getStringExtra("backdrop_path").toString()
        poster_path = intent.getStringExtra("poster_path").toString()
        release_date = intent.getStringExtra("release_date").toString()
        original_language = intent.getStringExtra("original_language").toString()
        vote_average = intent.getStringExtra("vote_average").toString()

        binding.imgBackArrow.setOnClickListener {
            onBackPressed()
        }

        binding.textViewTitle.text = original_title
        binding.textViewOverview.text = overview
        Glide.with(this).load(backdrop_path.getDownloadUrl()).into(binding.detailsImage)
        binding.textViewReleaseDate.text = release_date
        binding.textViewVoteAvg.text = vote_average
        binding.textViewOriginalLanguage.text = "Language: $original_language"

    }
}