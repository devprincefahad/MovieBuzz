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

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var topAdapter: TopMoviesAdapter
    private var topMovies = mutableListOf<Result>()
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel.getTopRatedMovies()

        binding.recyclerTop.setHasFixedSize(true)
        binding.recyclerTop.layoutManager =
            LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        topAdapter = TopMoviesAdapter(this@MainActivity, topMovies)
        binding.recyclerTop.adapter = topAdapter

        viewModel.topMoviesLD.observe(this) {
            if (it != null && !it.results.isNullOrEmpty()) {
                Log.d("mainactivity", it.results.toString())
                topMovies.clear()
                topMovies.addAll(it.results)
            }
        }

    }
}