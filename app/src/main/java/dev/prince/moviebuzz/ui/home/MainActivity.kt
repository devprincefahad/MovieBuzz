package dev.prince.moviebuzz.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.databinding.ActivityMainBinding
import dev.prince.moviebuzz.ui.adapter.GenreAdapter
import dev.prince.moviebuzz.ui.adapter.TopMoviesAdapter
import dev.prince.moviebuzz.ui.adapter.MoviesAdapter
import dev.prince.moviebuzz.ui.movies.MoviesActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen() // Complying with Android 12 Splash Screen guidelines

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.imgAllBookmarks.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }

        viewModel.topMovies.observe(this) {
            binding.recyclerTop.adapter = TopMoviesAdapter(this, it)
        }

        viewModel.upcomingMovies.observe(this) {
            binding.recyclerUpcoming.adapter = MoviesAdapter(this, it)
        }

        viewModel.genre.observe(this) {
            binding.recyclerCategories.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)
            binding.recyclerCategories.adapter = GenreAdapter(this@MainActivity, it)
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