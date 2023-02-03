package dev.prince.moviebuzz.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Genre
import dev.prince.moviebuzz.data.Movie
import dev.prince.moviebuzz.ui.home.MovieDetailsActivity
import dev.prince.moviebuzz.util.getDownloadUrl

class GenreMoviesAdapter(
    private val context: Context,
    private val genres: List<Movie>
) :
    RecyclerView.Adapter<GenreMoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreMoviesViewHolder =
        GenreMoviesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_upcoming, parent, false)
        )

    override fun onBindViewHolder(holder: GenreMoviesViewHolder, position: Int) {

        Glide.with(context).load(genres[position].backdrop_path?.getDownloadUrl())
            .into(holder.image)

        holder.title.text = genres[position].title
        holder.vote.text = genres[position].vote_average.toString()

        holder.upcomingLayout.setOnClickListener {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra("original_title", genres[position].original_title)
            intent.putExtra("overview", genres[position].overview)
            intent.putExtra("backdrop_path", genres[position].backdrop_path)
            intent.putExtra("release_date", genres[position].release_date)
            intent.putExtra("vote_average", genres[position].vote_average.toString())
            intent.putExtra("original_language", genres[position].original_language)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return genres.size
    }

}

class GenreMoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView = itemView.findViewById(R.id.textView_title)
    val vote: TextView = itemView.findViewById(R.id.textView_vote)
    val image: ImageView = itemView.findViewById(R.id.imageView_poster)
    val upcomingLayout: ConstraintLayout = itemView.findViewById(R.id.upcomingLayout)
}