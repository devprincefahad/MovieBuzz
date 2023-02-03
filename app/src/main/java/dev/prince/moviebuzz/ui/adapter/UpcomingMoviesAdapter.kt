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
import dev.prince.moviebuzz.ui.home.MovieDetailsActivity
import dev.prince.moviebuzz.util.getDownloadUrl

class UpcomingMoviesAdapter(
    val context: Context,
    private val upcomingMovies: List<Movie>
) : RecyclerView.Adapter<UpcomingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UpcomingMoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_upcoming, parent, false)
        )

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {

        Glide.with(context).load(upcomingMovies[position].backdrop_path?.getDownloadUrl())
            .into(holder.image)

        holder.title.text = upcomingMovies[position].title
        holder.vote.text = upcomingMovies[position].vote_average.toString()

        holder.upcomingLayout.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("original_title", upcomingMovies[position].original_title)
            intent.putExtra("overview", upcomingMovies[position].overview)
            intent.putExtra("backdrop_path", upcomingMovies[position].backdrop_path)
            intent.putExtra("release_date", upcomingMovies[position].release_date)
            intent.putExtra("vote_average", upcomingMovies[position].vote_average.toString())
            intent.putExtra("original_language", upcomingMovies[position].original_language)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = upcomingMovies.size

}

class UpcomingMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.textView_title)
    val image: ImageView = itemView.findViewById(R.id.imageView_poster)
    val vote: TextView = itemView.findViewById(R.id.textView_vote)
    val upcomingLayout: ConstraintLayout = itemView.findViewById(R.id.upcomingLayout)

}