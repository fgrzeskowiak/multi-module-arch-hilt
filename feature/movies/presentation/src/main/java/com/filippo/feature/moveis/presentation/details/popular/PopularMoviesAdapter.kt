package com.filippo.feature.moveis.presentation.details.popular

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.filippo.feature.moveis.presentation.databinding.ItemMovieBinding

internal class PopularMoviesAdapter(
    private val clickListener: (Int) -> Unit
) : PagingDataAdapter<MovieListItem, PopularMoviesAdapter.ViewHolder>(MovieDiffUtil) {

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieListItem) {
            binding.root.setOnClickListener { clickListener(movie.id) }
            binding.poster.setImageDrawable(movie.poster)
            binding.rating.text = movie.rating
            binding.releaseDate.text = movie.releaseDate
            binding.title.text = movie.title
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}

private object MovieDiffUtil : DiffUtil.ItemCallback<MovieListItem>() {
    override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean =
        oldItem == newItem

}
