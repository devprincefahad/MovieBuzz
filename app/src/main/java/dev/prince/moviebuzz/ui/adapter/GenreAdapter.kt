package dev.prince.moviebuzz.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import dev.prince.moviebuzz.R
import dev.prince.moviebuzz.data.Genre
import dev.prince.moviebuzz.databinding.ListItemGenreBinding
import dev.prince.moviebuzz.ui.movies.MoviesActivity

class GenreAdapter(
    private val context: Context,
    private val genres: List<Genre>
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder =
        CategoryViewHolder(
            ListItemGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.tvCategory.text = genres[position].name
        holder.binding.categoryCardView.setOnClickListener {
            val intent = Intent(context, MoviesActivity::class.java)
            intent.putExtra("id", genres[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

}

class CategoryViewHolder(val binding: ListItemGenreBinding) :
    RecyclerView.ViewHolder(binding.root) {
//    val tvCategory: TextView = itemView.findViewById(R.id.tv_category)
//    val categoryCardView: CardView = itemView.findViewById(R.id.categoryCardView)
}