package com.filippo.feature.search.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.filippo.feature.search.presentation.databinding.ItemSearchBinding

internal class SearchAdapter(
    private val clickListener: (Int) -> Unit
) : PagingDataAdapter<SearchListItem, SearchAdapter.ViewHolder>(SearchDiffUtil) {

    inner class ViewHolder(
        private val binding: ItemSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: SearchListItem) {
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
        ViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
}

private object SearchDiffUtil : DiffUtil.ItemCallback<SearchListItem>() {
    override fun areItemsTheSame(oldItem: SearchListItem, newItem: SearchListItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: SearchListItem, newItem: SearchListItem): Boolean =
        oldItem == newItem

}
