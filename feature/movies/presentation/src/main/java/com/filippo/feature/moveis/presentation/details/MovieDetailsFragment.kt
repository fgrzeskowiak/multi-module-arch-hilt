package com.filippo.feature.moveis.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.filippo.core.ui.BaseFragment
import com.filippo.feature.moveis.presentation.R
import com.filippo.feature.moveis.presentation.databinding.FragmentMovieDetailsBinding
import com.filippo.feature.moveis.presentation.details.details.FavouritesState
import com.filippo.feature.moveis.presentation.details.details.MovieDetailsViewModel
import com.google.android.material.chip.Chip
import com.filippo.feature.movies.domain.models.MovieDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    private val viewModel by viewModels<MovieDetailsViewModel>()

    override fun bindingFactory(inflater: LayoutInflater) =
        FragmentMovieDetailsBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        collectEvents()

        binding.addToFavourites.setOnClickListener { viewModel.addToFavourites() }
    }

    private fun collectEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest { state ->
                with(binding) {
                    progress.isVisible = state.isLoading
                    errorLayout.isVisible = state.isFailure
                    successLayout.isVisible = state.isSuccess
                }
                state.onSuccess(::setMovieDetails)
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.addToFavouritesState.collectLatest(::showToast)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.favouritesState.collectLatest { favouritesState ->
                with(binding) {
                    addToFavourites.isVisible = favouritesState is FavouritesState.Show
                    if (favouritesState is FavouritesState.Show) {
                        addToFavourites.text = getString(
                            if (favouritesState.isOnFavourites) {
                                R.string.remove_from_favourites
                            } else {
                                R.string.add_to_favourites
                            }
                        )
                    }
                }
            }
        }
    }

    private fun showToast(success: Boolean) {
        val resId = if (success) {
            R.string.add_to_favourites_success
        } else {
            R.string.add_to_favourites_error
        }
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show()
    }

    private fun setMovieDetails(movieDetails: MovieDetails) {
        with(binding) {
            image.setImageDrawable(movieDetails.poster)
            title.text = movieDetails.title
            releaseDate.text = movieDetails.releaseDate
            description.text = movieDetails.overview
            setupGenres(movieDetails.genres)
        }
    }

    private fun setupGenres(genres: List<MovieDetails.Genre>) {
        for (genre in genres) {
            val chipView = Chip(requireContext()).apply {
                id = View.generateViewId()
                text = genre.name
            }
            binding.root.addView(chipView)
            binding.genres.addView(chipView)
        }
    }
}
