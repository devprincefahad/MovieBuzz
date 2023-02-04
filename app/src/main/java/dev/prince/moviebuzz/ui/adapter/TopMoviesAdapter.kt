package dev.prince.moviebuzz.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.databinding.ListItemTopRatedBinding
import dev.prince.moviebuzz.ui.details.MovieDetailsActivity
import dev.prince.moviebuzz.util.getDownloadUrl

class TopMoviesAdapter(
    val context: Context,
    private val topMovies: List<Movie>
) : RecyclerView.Adapter<TopMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopMoviesViewHolder(
            ListItemTopRatedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {

        with(holder) {

            val movie = topMovies[position]

            Glide.with(context).load(movie.backdrop_path?.getDownloadUrl())
                .into(binding.imageViewPoster)

            binding.textViewTitle.text = movie.title
            binding.textViewVote.text = movie.vote_average.toString()

            binding.topCardView.setOnClickListener {
                val intent = Intent(context, MovieDetailsActivity::class.java)
                intent.putExtra("movie", movie)
                context.startActivity(intent)
            }
        }

    }

    override fun getItemCount(): Int = topMovies.size

}

class TopMoviesViewHolder(val binding: ListItemTopRatedBinding) :
    RecyclerView.ViewHolder(binding.root) {

//    val title: TextView = itemView.findViewById(R.id.textView_title)
//    val image: ImageView = itemView.findViewById(R.id.imageView_poster)
//    val vote: TextView = itemView.findViewById(R.id.textView_vote)
//    val topCardView: MaterialCardView = itemView.findViewById(R.id.topCardView)

}