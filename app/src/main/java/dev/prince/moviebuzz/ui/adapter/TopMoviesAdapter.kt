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

        Glide.with(context).load(topMovies[position].backdrop_path.getDownloadUrl()).into(holder.image)

        holder.title.text = topMovies[position].title
    }

    override fun getItemCount(): Int = topMovies.size

}

class TopMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.textView_title)
    val image:ImageView = itemView.findViewById(R.id.imageView_poster)

}