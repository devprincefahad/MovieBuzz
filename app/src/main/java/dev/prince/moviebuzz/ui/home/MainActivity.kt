package dev.prince.moviebuzz.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Result
import dev.prince.moviebuzz.databinding.ActivityMainBinding
import dev.prince.moviebuzz.ui.adapter.TopMoviesAdapter
import dev.prince.moviebuzz.ui.adapter.UpcomingMoviesAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getTopRatedMovies()
        viewModel.getGenres()

        binding.recyclerTop.setHasFixedSize(true)
        binding.recyclerTop.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL, false
        )

        viewModel.topMovies.observe(this) {
            binding.recyclerTop.adapter = TopMoviesAdapter(this, it.results)
        }

        viewModel.getUpcomingMovies()

        binding.recyclerUpcoming.setHasFixedSize(true)
        binding.recyclerUpcoming.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL, false
        )

        viewModel.upcomingMovies.observe(this){
            binding.recyclerUpcoming.adapter = UpcomingMoviesAdapter(this, it.results)
        }

    }
}