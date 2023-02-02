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
import dev.prince.moviebuzz.ui.home.MovieDetailsActivity
import dev.prince.moviebuzz.util.getDownloadUrl

class TopMoviesAdapter(
    val context: Context,
    private val topMovies: List<Movie>
) : RecyclerView.Adapter<TopMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopMoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_top_rated, parent, false)
        )

    override fun onBindViewHolder(holder: TopMoviesViewHolder, position: Int) {

        Glide.with(context).load(topMovies[position].backdrop_path.getDownloadUrl())
            .into(holder.image)

        holder.title.text = topMovies[position].title
        holder.vote.text = topMovies[position].vote_average.toString()

        holder.topCardView.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("original_title", topMovies[position].original_title)
            intent.putExtra("overview", topMovies[position].overview)
            intent.putExtra("backdrop_path", topMovies[position].backdrop_path)
            intent.putExtra("poster_path", topMovies[position].poster_path)
            intent.putExtra("release_date", topMovies[position].release_date)
            intent.putExtra("vote_average", topMovies[position].vote_average.toString())
            intent.putExtra("original_language", topMovies[position].original_language)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = topMovies.size

}

class TopMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.textView_title)
    val image: ImageView = itemView.findViewById(R.id.imageView_poster)
    val vote: TextView = itemView.findViewById(R.id.textView_vote)
    val topCardView: MaterialCardView = itemView.findViewById(R.id.topCardView)

}