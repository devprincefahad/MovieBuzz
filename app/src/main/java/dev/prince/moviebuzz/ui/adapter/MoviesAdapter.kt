package dev.prince.moviebuzz.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.databinding.ListItemMovieBinding
import dev.prince.moviebuzz.ui.details.MovieDetailsActivity
import dev.prince.moviebuzz.util.getDownloadUrl

class MoviesAdapter(
    val context: Context,
    private val upcomingMovies: List<Movie>
) : RecyclerView.Adapter<UpcomingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UpcomingMoviesViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )

        )

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {

        with(holder) {

            val movie = upcomingMovies[position]

            Glide.with(context).load(movie.backdrop_path?.getDownloadUrl())
                .into(binding.imageViewPoster)

            binding.textViewTitle.text = movie.title
            binding.textViewVote.text = movie.vote_average.toString()

            binding.upcomingLayout.setOnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movie", movie)
                context.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int = upcomingMovies.size

}

class UpcomingMoviesViewHolder(val binding: ListItemMovieBinding) :
    RecyclerView.ViewHolder(binding.root) {

//    val title: TextView = itemView.findViewById(R.id.textView_title)
//    val image: ImageView = itemView.findViewById(R.id.imageView_poster)
//    val vote: TextView = itemView.findViewById(R.id.textView_vote)
//    val upcomingLayout: ConstraintLayout = itemView.findViewById(R.id.upcomingLayout)

}