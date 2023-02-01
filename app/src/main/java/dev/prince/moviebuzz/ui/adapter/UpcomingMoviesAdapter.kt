package dev.prince.moviebuzz.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.util.getDownloadUrl

class UpcomingMoviesAdapter(
    val context: Context,
    private val topMovies: List<Movie>
) : RecyclerView.Adapter<UpcomingMoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UpcomingMoviesViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_upcoming, parent, false)
        )

    override fun onBindViewHolder(holder: UpcomingMoviesViewHolder, position: Int) {

        Glide.with(context).load(topMovies[position].backdrop_path.getDownloadUrl())
            .into(holder.image)

        holder.title.text = topMovies[position].title
//        holder.genre.text = topMovies[position].genre_ids..toString()
    }

    override fun getItemCount(): Int = topMovies.size

}

class UpcomingMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.textView_title)
    val image: ImageView = itemView.findViewById(R.id.imageView_poster)

}