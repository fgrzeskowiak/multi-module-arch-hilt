package com.filippo.feature.moveis.presentation.details.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.filippo.core.ui.BaseFragment
import com.filippo.feature.moveis.presentation.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
internal class PopularMoviesFragment : BaseFragment<FragmentMoviesBinding>() {
    private val viewModel by viewModels<PopularMoviesViewModel>()
    private val moviesAdapter by lazy { PopularMoviesAdapter(::onMovieClick) }

    override fun bindingFactory(inflater: LayoutInflater): FragmentMoviesBinding =
        FragmentMoviesBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        collectEvents()
    }

    private fun setupViews() {
        binding.movieList.apply {
            adapter = moviesAdapter
                .withLoadStateFooter(PopularMoviesLoadStateAdapter(moviesAdapter::retry))
        }
        binding.retryButton.setOnClickListener {
            moviesAdapter.retry()
        }
    }

    private fun collectEvents() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.popularMovies.collectLatest(moviesAdapter::submitData)
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            moviesAdapter.loadStateFlow.collect {
                val isInitialLoading = it.refresh is LoadState.Loading
                val isInitialError = it.refresh is LoadState.Error
                binding.errorLayout.isVisible = isInitialError
                binding.progress.isVisible = isInitialLoading
                binding.errorMessage.text = (it.refresh as? LoadState.Error)?.error?.message
            }
        }
    }

    private fun onMovieClick(movieId: Int) {
        findNavController().navigate(
            PopularMoviesFragmentDirections.openDetails(movieId)
        )
    }
}
